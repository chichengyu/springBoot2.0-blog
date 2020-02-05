package com.blog.controller;


import com.blog.pojo.Blog;
import com.blog.pojo.Tag;
import com.blog.pojo.Type;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.vo.BlogQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String types(@PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC)Pageable pageable,
                        @PathVariable("id") Long tagId,
                        Model model){
        // 查询所以分类
        List<Tag> tags = tagService.listTagTop(10000);
        if (tagId == -1){// 等于 -1 表示分类页默认选中的
            tagId = tags.get(0).getId();
        }
        Page<Blog> blogs = blogService.listBlogs(tagId,pageable);

        model.addAttribute("tags",tags);
        model.addAttribute("page",blogs);
        model.addAttribute("activeTagId",tagId);
        return "web/tags";
    }
}
