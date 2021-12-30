package com.example.unikomwebresfulapi.controller;

import com.example.unikomwebresfulapi.exception.NotFoundException;
import com.example.unikomwebresfulapi.model.Blog;
import com.example.unikomwebresfulapi.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/blogs")
public class BlogRestController {
    @Autowired
    private IBlogService blogService;

    @GetMapping
    public ResponseEntity<Iterable<Blog>> getAll() {
        Iterable blog = blogService.findAll();
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @GetMapping("/display")
    public ResponseEntity<Page<Blog>> getAllBlog(@PageableDefault(size = 5) Pageable pageable) {
        Page blogs = blogService.findAllBlog(pageable);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findById(@PathVariable Long id) {

        try {
            Optional<Blog> blogOptional = blogService.findById(id);
            return new ResponseEntity<>(blogOptional.get(), HttpStatus.OK);
        } catch (Exception e){
            throw  new NotFoundException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        blog.setDate(LocalDate.now());
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> editBlogById(@PathVariable Long id, @RequestBody Blog blog) {
        try {
            Optional<Blog> blogOptional = blogService.findById(id);
            blog.setId(blogOptional.get().getId());
            blog.setDate(LocalDate.now());
            return new ResponseEntity<>(blogService.save(blog), HttpStatus.OK);
        } catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlogById(@PathVariable Long id) {
        boolean isDelete = blogService.delete(id);
        if (!isDelete) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

