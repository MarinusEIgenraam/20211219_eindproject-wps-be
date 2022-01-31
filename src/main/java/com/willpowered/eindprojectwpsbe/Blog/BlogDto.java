package com.willpowered.eindprojectwpsbe.Blog;

import com.willpowered.eindprojectwpsbe.auth.UserDto;
import lombok.var;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import java.time.Instant;

public class BlogDto {

    public Long blogId;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    public String blogName;
    public String url;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    public String description;
    public String imageUrl;
    public Instant startTime;
    public UserDto blogOwner;

    public static BlogDto fromBlog(Blog blog) {
        var dto = new BlogDto();

        dto.blogId = blog.getBlogId();
        dto.blogName = blog.getBlogName();
        dto.url = blog.getUrl();
        dto.imageUrl = blog.getImageUrl();
        dto.description = blog.getDescription();
        dto.startTime = blog.getStartTime();
        dto.blogOwner = UserDto.fromUser(blog.getBlogOwner());

        return dto;
    }
}