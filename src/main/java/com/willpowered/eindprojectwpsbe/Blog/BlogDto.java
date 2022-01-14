package com.willpowered.eindprojectwpsbe.Blog;

import com.willpowered.eindprojectwpsbe.auth.User.UserDto;
import lombok.var;

import java.time.Instant;

public class BlogDto {

    public Long blogId;
    public String blogName;
    public String url;
    public String description;
    public Instant startTime;
    public UserDto blogOwner;

    public static BlogDto fromBlog(Blog blog) {
        var dto = new BlogDto();

        dto.blogId = blog.getBlogId();
        dto.blogName = blog.getBlogName();
        dto.url = blog.getUrl();
        dto.description = blog.getDescription();
        dto.startTime = blog.getStartTime();
        dto.blogOwner = UserDto.fromUser(blog.getBlogOwner());

        return dto;
    }
}