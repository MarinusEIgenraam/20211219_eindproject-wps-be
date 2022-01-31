package com.willpowered.eindprojectwpsbe.Blog;

import com.willpowered.eindprojectwpsbe.Project.ProjectDto;
import com.willpowered.eindprojectwpsbe.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "blogId") String[] sort,
            @RequestParam(value = "blogOwner", required = false) String blogOwner
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
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
    public BlogDto updateBlog(@PathVariable Long id, @RequestBody BlogInputDto dto) {
        blogService.updateBlog(id, dto.toBlog());
        return BlogDto.fromBlog(dto.toBlog());
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
    }
}