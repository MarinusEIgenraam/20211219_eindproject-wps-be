package com.willpowered.eindprojectwpsbe.ProfileImage;


import com.willpowered.eindprojectwpsbe.Portal.Portal;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileImageRepository extends CrudRepository<ProfileImage, Long> {
    Optional<ProfileImage> findByPortal(Portal portal);
}
