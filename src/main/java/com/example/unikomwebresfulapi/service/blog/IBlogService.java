package com.example.unikomwebresfulapi.service.blog;

import com.example.unikomwebresfulapi.model.Blog;
import com.example.unikomwebresfulapi.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IGeneralService<Blog> {
    Page<Blog> findAllBlog(Pageable pageable);

    Page<Blog> findBlogExist(Pageable pageable);
}
