package com.blog.controller;


import com.blog.pojo.Blog;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.blog.util.MarkdownUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
     * 首页
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlogs(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(6));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogsTop(6));
        return "web/index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,String query,Model model){
        model.addAttribute("page",blogService.listBlogs("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "web/search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id,Model model){
        Blog blog = blogService.getBlogById(id);
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        model.addAttribute("blog",blog);
        return "web/blog";
    }

    // 底部文章推荐
    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogsTop(3));
        return "_fragment_web :: newblogList";
    }
}
