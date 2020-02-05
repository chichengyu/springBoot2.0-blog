package com.blog.service;

import com.blog.pojo.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 通过文章id获取评论
     * @param blogId
     * @return
     */
    List<Comment> listCommentByBlodId(Long blogId);

    /**
     * 新增 评论
     * @param comment
     * @return
     */
    Comment saveComment(Comment comment);
}
