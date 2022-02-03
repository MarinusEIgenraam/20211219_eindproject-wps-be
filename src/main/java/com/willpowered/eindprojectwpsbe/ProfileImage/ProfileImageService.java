package com.willpowered.eindprojectwpsbe.ProfileImage;


import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Portal.PortalRepository;
import com.willpowered.eindprojectwpsbe.auth.User;
import com.willpowered.eindprojectwpsbe.auth.UserAuthenticateService;
import com.willpowered.eindprojectwpsbe.exception.FileStorageException;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfileImageService {

    @Value("${app.upload.dir:${user.home}}")
    private String uploadDirectory;  // relative to root
    private final Path uploads = Paths.get("uploads");

    @Autowired
    private ProfileImageRepository profileImageRepository;
    @Autowired
    private PortalRepository portalRepository;
    @Autowired
    private UserAuthenticateService userAuthenticateService;

    public Iterable<ProfileImage> getFiles() {
        return profileImageRepository.findAll();
    }


    public long uploadFile(MultipartFile multipartFile) throws IOException {
        User currentUser = userAuthenticateService.getCurrentUser();
        Optional<Portal> optionalPortal = portalRepository.findByUser(currentUser);
        Path uploadDir = Paths.get("uploads/"+currentUser.getUsername());


        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadDir.resolve(originalFilename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + originalFilename, ioe);
        }

        ProfileImage newProfileImage = new ProfileImage();
        newProfileImage.setFileName(currentUser.getUsername()+"_profileImage");
        newProfileImage.setLocation(originalFilename);
        newProfileImage.setTitle("profileImage");
        if (optionalPortal.isPresent()) {
            Portal portal = optionalPortal.get();
            newProfileImage.setPortal(portal);
        }

        ProfileImage saved = profileImageRepository.save(newProfileImage);

        return saved.getId();
    }


    public void deleteFile(long id) {
        Optional<ProfileImage> stored = profileImageRepository.findById(id);

        if (stored.isPresent()) {
            String filename = stored.get().getFileName();
            Path location = this.uploads.resolve(filename);
            try {
                Files.deleteIfExists(location);
            }
            catch (IOException ex) {
                throw new RuntimeException("File not found");
            }

            profileImageRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    public ProfileImageDto getFileByPortal(Portal portal) {
        Optional<ProfileImage> optionalImage = profileImageRepository.findByPortal(portal);

        if (optionalImage.isPresent()) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand("download").toUri();

            ProfileImageDto profileImageDto = new ProfileImageDto();
            profileImageDto.fromProfileImage(optionalImage.get());
            profileImageDto.downloadUri = uri.toString();

            return profileImageDto;
        }
        else {
            throw new RecordNotFoundException("This image does not exist in our files");
        }
    }

    public ProfileImageDto getFileById(long id) {
        Optional<ProfileImage> optionalImage = profileImageRepository.findById(id);

        if (optionalImage.isPresent()) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand("download").toUri();

            ProfileImageDto profileImageDto = new ProfileImageDto();
            profileImageDto.fromProfileImage(optionalImage.get());
            profileImageDto.downloadUri = uri.toString();

            return profileImageDto;
        }
        else {
            throw new RecordNotFoundException("This image does not exist in our files");
        }
    }

    public boolean fileExistsById(long id) {
        return profileImageRepository.existsById(id);
    }

    public Resource downloadFile(long id) {
        Optional<ProfileImage> optionalImage = profileImageRepository.findById(id);

        if (optionalImage.isPresent()) {
            String filename = optionalImage.get().getFileName();
            Path path = this.uploads.resolve(filename);

            Resource resource = null;
            try {
                resource = new UrlResource(path.toUri());
                return resource;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new RecordNotFoundException();
        }

        return null;
    }

}