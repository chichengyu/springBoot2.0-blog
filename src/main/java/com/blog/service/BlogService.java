package com.blog.service;

import com.blog.pojo.Blog;
import com.blog.vo.BlogQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog saveBlog(Blog blog);

    Blog getBlogById(Long id);

    Blog updateBlog(Long id,Blog blog);

    Page<Blog> listBlogs(Pageable pageable);

    Page<Blog> listBlogs(Pageable pageable, BlogQueryVo blogQueryVo);

    void deleteBlog(Long id);

    // ******************* 前端 ***********************
    List<Blog> listRecommendBlogsTop(Integer size);
    // 搜索
    Page<Blog> listBlogs(String query,Pageable pageable);
    // 根据标签 id 查询 blog
    Page<Blog> listBlogs(Long tagId,Pageable pageable);

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();
}
