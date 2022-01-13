package com.willpowered.eindprojectwpsbe.dto.elements.Blog;

import com.willpowered.eindprojectwpsbe.model.elements.Blog;
import lombok.var;

import java.time.Instant;

public class BlogInputDto {

    public Long blogId;
    public String blogName;
    public String url;
    public String description;
    public Instant startTime;
    public String blogOwnerId;

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