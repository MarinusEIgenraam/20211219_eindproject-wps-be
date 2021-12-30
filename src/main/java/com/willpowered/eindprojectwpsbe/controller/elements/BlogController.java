package com.willpowered.eindprojectwpsbe.controller.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.Blog.BlogDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Blog.BlogInputDto;
import com.willpowered.eindprojectwpsbe.model.elements.Blog;
import com.willpowered.eindprojectwpsbe.service.elements.BlogService;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blogs")
@AllArgsConstructor
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/{id}")
    public BlogDto getBlog(@PathVariable("id") Long id) {
        var blog = blogService.getBlog(id);
        return BlogDto.fromBlog(blog);
    }

    @GetMapping()
    public List<BlogDto> getBlogs() {
        var dtos = new ArrayList<BlogDto>();
        List<Blog> blogs;

        blogs = blogService.getBlogs();
        for (Blog blog : blogs) {
            dtos.add(BlogDto.fromBlog(blog));
        }
        return dtos;
    }

    @PostMapping
    public BlogDto saveBlog(@RequestBody BlogInputDto dto) {
        var blog = blogService.saveBlog(dto.toBlog());
        return BlogDto.fromBlog(blog);
    }

    @PutMapping("/{id}")
    public BlogDto updateBlog(@PathVariable Long id, @RequestBody BlogInputDto dto) {
        blogService.updateBlog(id, dto.toBlog());
        return BlogDto.fromBlog(dto.toBlog());
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
    }
}