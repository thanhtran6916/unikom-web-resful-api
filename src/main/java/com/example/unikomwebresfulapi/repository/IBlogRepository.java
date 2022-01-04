package com.example.unikomwebresfulapi.repository;


import com.example.unikomwebresfulapi.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Long> {

    @Query(value = "SELECT b FROM Blog b WHERE b.headline like %:headline% and b.author like %:author% and b.isDeleted = false ")
    Page<Blog> findBlogExist(@Param("headline") String headline,
                             @Param("author") String author,
                             Pageable pageable);
}
