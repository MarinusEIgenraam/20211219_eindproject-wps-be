package com.willpowered.eindprojectwpsbe.Blog;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/blogs")
@AllArgsConstructor
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/{id}")
    public BlogDto getBlog(@PathVariable("id") Long id) {
        var blog = blogService.getBlog(id);
        return BlogDto.fromBlog(blog);
    }

    @GetMapping
    public Page<BlogDto> getBlogsFor(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "sort", defaultValue = "startTime", required = false) String[] sort,
            @RequestParam(value = "blogOwner", required = false) String blogOwner
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        var dtos = new ArrayList<BlogDto>();

        Page<Blog> blogs;
        if (blogOwner != null) {
            blogs = blogService.getBlogsForBlogOwner(blogOwner, pageable);
        } else {
            blogs = blogRepository.findAll(pageable);
        }

        for (Blog blog : blogs) {
            dtos.add(BlogDto.fromBlog(blog));
        }

        Page<BlogDto> pageOfBlogs = new PageImpl<>(dtos);

        return pageOfBlogs;
    }

    @PostMapping
    public BlogDto saveBlog(@RequestBody BlogInputDto dto) {
        var blog = blogService.saveBlog(dto.toBlog());
        return BlogDto.fromBlog(blog);
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public BlogDto updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        blogService.updateBlog(id, blog);
        return BlogDto.fromBlog(blog);
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
    }
}