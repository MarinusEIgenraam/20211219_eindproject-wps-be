package com.willpowered.eindprojectwpsbe.Blog;

import com.willpowered.eindprojectwpsbe.auth.Models.User;
import com.willpowered.eindprojectwpsbe.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getBlogsForBlowOwner(String blogOwner) {
        var optionalUser = userRepository.findById(blogOwner);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return blogRepository.findAllByBlogOwner(user);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    public Blog getBlog(Long blogId) {
        Optional<Blog> blog = blogRepository.findById(blogId);

        if (blog.isPresent()) {
            return blog.get();
        } else {
            throw new RecordNotFoundException("Blog does not exist");
        }
    }

    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public void updateBlog(Long id, Blog blog) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isPresent()) {
            blogRepository.deleteById(id);
            blogRepository.save(blog);
        } else {
            throw new RecordNotFoundException("Blog does not exist");
        }
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
