package com.willpowered.eindprojectwpsbe.Blog;


import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
import com.willpowered.eindprojectwpsbe.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    //////////////////////////////
    //// Create

    public Blog saveBlog(Blog blog) {
        blog.setBlogOwner(userService.getCurrentUser());
        return blogRepository.save(blog);
    }

    //////////////////////////////
    //// Read

    public Page<Blog> getBlogsForBlogOwner(String username, Pageable pageable) {
        var optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return blogRepository.findAllByBlogOwner(user, pageable);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }


    public Blog getBlog(Long blogId) {
        var blog = blogRepository.findById(blogId);

        if (blog.isPresent()) {
            return blog.get();
        } else {
            throw new RecordNotFoundException("Blog does not exist");
        }
    }

    //////////////////////////////
    //// Update

    public void updateBlog(Long id, Blog blog) {
        var optionalBlog = blogRepository.findById(blog.getBlogId());
        if (optionalBlog.isPresent()) {
            blogRepository.save(blog);
        } else {
            throw new RecordNotFoundException("Blog does not exist");
        }
    }

    //////////////////////////////
    //// Delete

    public void deleteBlog(Long id) {
        var optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isPresent()) {
            blogRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Blog does not exist");
        }
    }
}
