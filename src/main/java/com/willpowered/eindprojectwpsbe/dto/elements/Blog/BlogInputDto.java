package com.willpowered.eindprojectwpsbe.dto.elements.Blog;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Blog;
import lombok.var;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

public class BlogInputDto {

    public Long blogId;
    public String blogName;
    public String url;
    public String description;
    public Instant startTime;
    public Long blogOwnerId;

    public Blog toBlog() {
        var blog = new Blog();

        blog.setBlogId(blogId);
        blog.setBlogName(blogName);
        blog.setUrl(url);
        blog.setDescription(description);
        blog.setStartTime(startTime);

        return blog;
    }
}