package com.blog.controller;


import com.blog.pojo.Blog;
import com.blog.pojo.Tag;
import com.blog.service.BlogService;
import com.blog.service.TagService;
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
public class AboutShowController {

    @GetMapping("/about")
    public String types(){
        return "web/about";
    }
}
