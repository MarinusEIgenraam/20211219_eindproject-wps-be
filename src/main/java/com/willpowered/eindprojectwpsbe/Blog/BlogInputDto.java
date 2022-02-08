package com.willpowered.eindprojectwpsbe.Blog;


import org.hibernate.annotations.Type;

import java.time.Instant;

public class BlogInputDto {

    public Long blogId;
    @Type(type = "org.hibernate.type.TextType")
    public String blogName;
    public String url;
    public String imageUrl;
    @Type(type = "org.hibernate.type.TextType")
    public String description;
    public Instant startTime;

    public Blog toBlog() {
        var blog = new Blog();

        blog.setBlogId(blogId);
        blog.setBlogName(blogName);
        blog.setUrl(url);
        blog.setImageUrl(imageUrl);
        blog.setDescription(description);
        blog.setStartTime(startTime);

        return blog;
    }
}