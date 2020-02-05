package com.blog.service.impl;

import com.blog.exception.NotFoundException;
import com.blog.pojo.Blog;
import com.blog.pojo.Tag;
import com.blog.pojo.Type;
import com.blog.repository.BlogRepository;
import com.blog.service.BlogService;
import com.blog.vo.BlogQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    /**
     * 文章列表
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }


    /**
     * 搜索条件查询
     * @param pageable
     * @param blogQueryVo
     * @return
     */
    @Override
    public Page<Blog> listBlogs(Pageable pageable, BlogQueryVo blogQueryVo) {
        return blogRepository.findAll(new Specification<Blog>() {
            /**
             * @param root  要查询的映射对象
             * @param query 添加查询条件
             * @param cb 构建 Predicate
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!ObjectUtils.isEmpty(blogQueryVo.getTitle())){
                    predicates.add(cb.like(root.<String>get("title"),"%"+blogQueryVo.getTitle()+"%"));
                }
                if (!ObjectUtils.isEmpty(blogQueryVo.getTypeId())){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blogQueryVo.getTypeId()));
                }
                if (blogQueryVo.isRecommend()){
                    predicates.add(cb.equal(root.get("recommend"),blogQueryVo.isRecommend()));
                }
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    @Transactional
    public Blog saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogRepository.save(blog);
    }

    @Override
    @Transactional
    public Blog getBlogById(Long id) {
        // 更新流量次数
        blogRepository.updateViews(id);
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Blog updateBlog(Long id, Blog blog) {
        Blog blogById = getBlogById(id);
        if (blogById == null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blogById,blog);
        blog.setId(id);
        blog.setUpdateTime(new Date());
        return blogRepository.save(blog);
    }

    @Override
    @Transactional
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    // ******************* 前端 ***********************
    @Override
    public List<Blog> listRecommendBlogsTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        return blogRepository.findTop(PageRequest.of(0,size,sort));
    }

    @Override
    public Page<Blog> listBlogs(String query, Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }

    // 根据标签 id 查询 blog
    @Override
    public Page<Blog> listBlogs(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // blog 通过属性 tags 关联
                Join join = root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        }, pageable);
    }

    // 归档
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> groupYear = blogRepository.findGroupYear();
        List<Blog> all = blogRepository.findAll();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Map<String,List<Blog>> map = new HashMap<>();
        for (String year : groupYear){
            // blogRepository.findByYear(year)
            ArrayList<Blog> blogs = new ArrayList<>();
            for (Blog blog : all){
                String date = simpleDateFormat.format(blog.getUpdateTime());
                if (year.equals(date)){
                    blogs.add(blog);
                }
            }
            map.put(year,blogs);
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }
}
