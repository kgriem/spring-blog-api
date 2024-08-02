package com.cogent.blog.rest.api.service;

import com.cogent.blog.rest.api.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    Comment createComment(Long postId, Comment comment);
    List<Comment> getCommentsByPost(Long postId);
    Comment getCommentById(Long postId, Long id);
    Comment updateComment(Long postId, Long id, Comment updateComment);
    void deleteComment(Long postId, Long id);
}
