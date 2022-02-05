package com.willpowered.eindprojectwpsbe.ProfileImage;


import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Portal.PortalRepository;
import com.willpowered.eindprojectwpsbe.Portal.PortalService;
import com.willpowered.eindprojectwpsbe.auth.User;
import com.willpowered.eindprojectwpsbe.auth.UserAuthenticateService;
import com.willpowered.eindprojectwpsbe.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.auth.UserService;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final Path uploadDir = Paths.get("uploads/");

    @Autowired
    private ProfileImageRepository profileImageRepository;
    @Autowired
    private PortalRepository portalRepository;
    @Autowired
    private UserAuthenticateService userAuthenticateService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PortalService portalService;
    @Autowired
    UserService userService;

    public Iterable<ProfileImage> getFiles() {
        return profileImageRepository.findAll();
    }


    public long uploadFile(MultipartFile multipartFile) throws IOException {
        User currentUser = userAuthenticateService.getCurrentUser();
        Optional<Portal> optionalPortal = portalRepository.findByUser(currentUser);

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
        newProfileImage.setFileName(originalFilename);
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
            Path location = uploadDir.resolve(filename);
            try {
                Files.deleteIfExists(location);
            } catch (IOException ex) {
                throw new RuntimeException("File not found");
            }

            profileImageRepository.deleteById(id);
        } else {
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
        } else {
            throw new RecordNotFoundException("This image does not exist in our files");
        }
    }

    public Resource downloadFile(String username) {
        Optional<ProfileImage> optionalImage = profileImageRepository.findByPortal(portalService.getUserPortal(userService.getUser(username)));

        if (optionalImage.isPresent()) {
            String filename = optionalImage.get().getFileName();
            Path fileLocation = uploadDir.resolve(filename);

            Resource resource = null;
            try {
                resource = new UrlResource(fileLocation.toUri());
                return resource;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            throw new RecordNotFoundException();
        }

        return null;
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
        } else {
            throw new RecordNotFoundException("This image does not exist in our files");
        }
    }

    public boolean fileExistsById(long id) {
        return profileImageRepository.existsById(id);
    }

}