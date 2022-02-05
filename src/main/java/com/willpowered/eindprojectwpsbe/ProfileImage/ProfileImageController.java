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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/files")
@CrossOrigin
public class ProfileImageController {

    @Autowired
    ProfileImageService profileImageService;
    @Autowired
    UserService userService;
    @Autowired
    PortalService portalService;

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
        User user = userService.getUser(username);
        Portal portal = portalService.getUserPortal(user);
        return profileImageService.getFileByPortal(portal);
    }

    @GetMapping("/{username}/download")
    public ResponseEntity downloadFile(@PathVariable String username) {
        Resource resource = profileImageService.downloadFile(username);
        String mediaType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> uploadFile(@RequestPart MultipartFile document) throws IOException {
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
