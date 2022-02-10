package com.willpowered.eindprojectwpsbe;

import com.willpowered.eindprojectwpsbe.Alert.Alert;
import com.willpowered.eindprojectwpsbe.Blog.Blog;
import com.willpowered.eindprojectwpsbe.Comment.Comment;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.User.User;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class TestEntityService {

    public User makeUser(String name) {
        return User.builder()
                .password("password" + name)
                .email(name + "@" + name + ".nl")
                .enabled(true)
                .username(name)
                .build();
    }

    public Project makeProject(String name) {
        return Project.builder()
                .projectName(name)
                .projectId((long) new Random().nextInt())
                .url("wwww"+name+".nl")
                .imageUrl("www"+name+"image.nl")
                .projectOwner(makeUser(name+"Owner"))
                .voteCount(new Random().nextInt())
                .build();
    }

    public Blog makeBlog(String name) {
        return Blog.builder()
                .blogOwner(makeUser(name))
                .blogId((long) new Random().nextInt())
                .startTime(Instant.now())
                .blogName(name)
                .description(name + name)
                .url("wwww"+name+".nl")
                .imageUrl("www"+name+"image.nl")
                .build();
    }

    public Comment makeComment(String name) {
        return Comment.builder()
                .id((long) new Random().nextInt())
                .user(makeUser(name))
                .text(name)
                .startTime(LocalDateTime.now())
                .build();
    }

    public Alert makeAlert(String name) {
        User user = makeUser(name);

        return Alert.builder()
                .title(name)
                .portal(makePortal(name))
                .createdAt(LocalDate.now())
                .text(name+user.getUsername())
                .id((long) new Random().nextInt())
                .build();
    }

    public Portal makePortal(String name) {
        return Portal.builder()
                .user(makeUser(name))
                .id((long) new Random().nextInt())
                .build();
    }
}
