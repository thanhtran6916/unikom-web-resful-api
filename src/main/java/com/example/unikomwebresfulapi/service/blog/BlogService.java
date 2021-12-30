package com.example.unikomwebresfulapi.service.blog;

import com.example.unikomwebresfulapi.model.Blog;
import com.example.unikomwebresfulapi.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {

    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if (!blogOptional.isPresent()) {
            return false;
        }
        Blog blog = blogOptional.get();
        blog.setDeleted(true);
        blogRepository.save(blog);
        return true;
    }

    @Override
    public Page<Blog> findAllBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findBlogExist(Pageable pageable) {
        return null;
    }
}