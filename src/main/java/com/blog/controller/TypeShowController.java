package com.blog.controller;


import com.blog.pojo.Blog;
import com.blog.pojo.Type;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC)Pageable pageable,
                        @PathVariable("id") Long id,
                        Model model){
        // 查询所以分类
        List<Type> types = typeService.listTypeTop(10000);
        if (id == -1){// 等于 -1 表示分类页默认选中的
            id = types.get(0).getId();
        }
        BlogQueryVo blogQueryVo = new BlogQueryVo();
        blogQueryVo.setTypeId(id);
        Page<Blog> blogs = blogService.listBlogs(pageable, blogQueryVo);

        model.addAttribute("types",types);
        model.addAttribute("page",blogs);
        model.addAttribute("activeTypeId",id);
        return "web/types";
    }
}
