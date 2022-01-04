package com.example.unikomwebresfulapi.service.blog;

import com.example.unikomwebresfulapi.dto.request.BlogRequest;
import com.example.unikomwebresfulapi.dto.response.BlogResponse;
import com.example.unikomwebresfulapi.exception.ApplicationException;
import com.example.unikomwebresfulapi.model.Blog;
import com.example.unikomwebresfulapi.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService{

    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Page<BlogResponse> findAll() {
        return null;
    }

    @Override
    public BlogResponse findById(Long id) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if (!blogOptional.isPresent()) {
            throw new ApplicationException("err.not-found");
        }
        return new BlogResponse(blogOptional.get());
    }

    @Override
    public BlogResponse save(BlogRequest blogRequest) {
        Blog blog = new Blog(blogRequest);
        return new BlogResponse(blogRepository.save(blog));
    }

    @Override
    public BlogResponse edit(Long id, BlogRequest blogRequest) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        blogOptional.orElseThrow(() -> new ApplicationException("err.not-found"));
        blogRequest.setId(id);
        Blog blog = new Blog(blogRequest);
        return new BlogResponse(blogRepository.save(blog)) ;
    }

    @Override
    public void delete(Long id) {
        Optional<Blog> oldBlogOptional = blogRepository.findById(id);
        Blog blog = oldBlogOptional.orElseThrow(() -> new ApplicationException("err.not-found"));
        blog.setDeleted(true);
        blogRepository.save(blog);
    }

    @Override
    public Page<BlogResponse> findBlogExist(String headline, String author, Pageable pageable) {
        Page<Blog> blogs = blogRepository.findBlogExist(headline, author, pageable);
        if (blogs == null) {
            throw new ApplicationException("er.not-found");
        }
        Page<BlogResponse> blogResponses = blogs.map(BlogResponse::new);
        return blogResponses;
    }
}
