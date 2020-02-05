package com.blog.service.impl;

import com.blog.pojo.Comment;
import com.blog.repository.CommentRepository;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public List<Comment> listCommentByBlodId(Long blogId) {
        List<Comment> commentList = commentRepository.findByBlogIdAndParentCommentNull(blogId, new Sort(Sort.Direction.ASC, "createTime"));
        childComment(commentList);
        return commentList;
    }

    private void childComment(List<Comment> comments){
        for (Comment comment : comments) {
            List<Comment> replys = comment.getReplyComments();
            for(Comment reply1 : replys) {
                //循环迭代，找出子代，存放在tempReplys中
                tempReplys.add(reply1);//顶节点添加到临时存放集合
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    private List<Comment> tempReplys = new ArrayList<>();
    private void recursively(Comment comment){
        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }

    @Override
    public Comment saveComment(Comment comment) {
        Long parentId = comment.getParentComment().getId();
        // 等于 -1 表示当前是对文章进行评论，不是回复别人的评论
        if (parentId != -1){
            comment.setParentComment(commentRepository.getOne(parentId));
        }else{
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }
}
