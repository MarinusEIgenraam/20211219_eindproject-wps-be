package com.willpowered.eindprojectwpsbe.ProfileImage;


import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Portal.PortalDto;
import com.willpowered.eindprojectwpsbe.Portal.PortalRepository;
import com.willpowered.eindprojectwpsbe.Portal.PortalService;
import com.willpowered.eindprojectwpsbe.auth.User;
import com.willpowered.eindprojectwpsbe.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.auth.UserService;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/files")
@CrossOrigin
public class ProfileImageController {

    @Autowired
    ProfileImageService profileImageService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PortalRepository portalRepository;

    @GetMapping("/all")
    public ResponseEntity<Object> getFiles() {
        Iterable<ProfileImage> files = profileImageService.getFiles();
        return ResponseEntity.ok().body(files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFileInfo(@PathVariable long id) {
        ProfileImageDto profileImageDto = profileImageService.getFileById(id);
        return ResponseEntity.ok().body(profileImageDto);
    }


    @GetMapping("/profiles/{username}")
    public ProfileImageDto getProfileImage(@PathVariable("username") String username) {
        var optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            var optionalPortal = portalRepository.findByUser(user);
            if (optionalPortal.isPresent()) {
                Portal portal = optionalPortal.get();
                return profileImageService.getFileByPortal(portal);
            } else {
                throw new RecordNotFoundException("This user doesnt have a portal yet");
            }
        } else {
            throw new RecordNotFoundException("I cant find the requested  user");
        }
    }

    @GetMapping("/{id}/download")
    public ResponseEntity downloadFile(@PathVariable long id) {
        Resource resource = profileImageService.downloadFile(id);
        String mediaType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping(value = "",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> uploadFile(@RequestPart MultipartFile document) {
        long newId = profileImageService.uploadFile(document);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFile(@PathVariable long id) {
        profileImageService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }

}
