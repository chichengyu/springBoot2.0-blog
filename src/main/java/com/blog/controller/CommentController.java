package com.blog.controller;

import com.blog.pojo.Comment;
import com.blog.pojo.User;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    /**
     * 获取留言列表
     * @param blogId
     * @param model
     * @return
     */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable("blogId")Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlodId(blogId));
        return "web/blog :: commentList";
    }

    /**
     * 回复评论
     * @param comment
     * @return
     */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession httpSession){
        comment.setAvatar(avatar);
        comment.setBlog(blogService.getBlogById(comment.getBlog().getId()));
        User user = (User)httpSession.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+comment.getBlog().getId();
    }
}
