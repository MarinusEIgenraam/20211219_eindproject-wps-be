package com.willpowered.eindprojectwpsbe.Blog;

import com.willpowered.eindprojectwpsbe.exception.BadRequestException;
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

    @GetMapping
    public List<BlogDto> getBlogsFor(
            @RequestParam(value = "blogOwner", required = false) String blogOwner
    ) {
        var dtos = new ArrayList<BlogDto>();

        List<Blog> blogs;
        if (blogOwner != null) {
            blogs = blogService.getBlogsForBlogOwner(blogOwner);
        } else {
            throw new BadRequestException();
        }

        for (Blog blog : blogs) {
            dtos.add(BlogDto.fromBlog(blog));
        }

        return dtos;
    }

    @GetMapping("/all")
    public List<BlogDto> getAllBlogs() {
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