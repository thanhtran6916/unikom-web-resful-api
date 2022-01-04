package com.example.unikomwebresfulapi.service.blog;

import com.example.unikomwebresfulapi.dto.request.BlogRequest;
import com.example.unikomwebresfulapi.dto.response.BlogResponse;
import com.example.unikomwebresfulapi.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IBlogService extends IGeneralService<BlogRequest,BlogResponse> {

    Page<BlogResponse> findBlogExist(@Param("headline") String headline, @Param("author") String author, Pageable pageable);
}
