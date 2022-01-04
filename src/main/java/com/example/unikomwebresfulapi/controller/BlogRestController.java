package com.example.unikomwebresfulapi.controller;

import com.example.unikomwebresfulapi.dto.request.BlogRequest;
import com.example.unikomwebresfulapi.dto.response.BlogResponse;
import com.example.unikomwebresfulapi.helper.ResultResp;
import com.example.unikomwebresfulapi.service.blog.BlogService;
import com.example.unikomwebresfulapi.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/blogs")
@CrossOrigin("*")
public class BlogRestController {

    @Autowired
    private IBlogService blogService;

    @GetMapping
    public ResultResp findAll(@RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam Optional<String> q,
                              @RequestParam Optional<String> s) {
        Pageable pageable = PageRequest.of(page, size);
        String headline = q.orElse("");
        String author = s.orElse("");
        return new ResultResp(blogService.findBlogExist(headline, author, pageable));
    }

    @GetMapping("{id}")
    public ResultResp findById(@PathVariable Long id) {
        return new ResultResp(blogService.findById(id));
    }

    @PostMapping
    public ResultResp save(@RequestBody BlogRequest blogRequest) {
        return new ResultResp(blogService.save(blogRequest));
    }

    @PutMapping("{id}")
    public ResultResp edit(@PathVariable Long id, @RequestBody BlogRequest blogRequest) {
        BlogResponse blogResponse = blogService.edit(id, blogRequest);
        return new ResultResp(blogResponse);
    }

    @DeleteMapping("{id}")
    public ResultResp delete(@PathVariable Long id) {
        blogService.delete(id);
        return new ResultResp();
    }
}
