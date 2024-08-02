package com.cogent.blog.rest.api.service.impl;

import com.cogent.blog.rest.api.entity.Comment;
import com.cogent.blog.rest.api.entity.Post;
import com.cogent.blog.rest.api.repository.CommentRepository;
import com.cogent.blog.rest.api.repository.PostRepository;
import com.cogent.blog.rest.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId).orElseThrow(()->new RuntimeException("resource not found"));
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPost(Long postId) {
        List<Comment> comments = new ArrayList<>();
        List<Comment> allComments = commentRepository.findAll();
        for(Comment c : allComments){
            if(c.getPost().getId().equals(postId)){
                comments.add(c);
            }
        }
        return comments;
    }

    @Override
    public Comment getCommentById(Long postId, Long id) {
        Post post = postRepository.findById(postId).orElseThrow(()->new RuntimeException("resource not found"));
        Comment comment = commentRepository.findById(id).orElseThrow(()->new RuntimeException("could not find comment by id: "+id));
        if(!post.getId().equals(postId)){
            throw new RuntimeException("post ids do not match");
        }
        return comment;
    }

    @Override
    public Comment updateComment(Long postId, Long id, Comment updateComment) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->new RuntimeException("could not find comment by id: "+id));
        Post post = postRepository.findById(postId).orElseThrow(()->new RuntimeException("not found"));
        if(!post.getId().equals(postId)){
            throw new RuntimeException("post ids do not match");
        }
        comment.setBody(updateComment.getBody());
        comment.setPost(post);
        comment.setName(updateComment.getName());
        comment.setEmail(updateComment.getEmail());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long postId, Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->new RuntimeException("could not find comment by id: "+id));
        Post post = postRepository.findById(postId).orElseThrow(()->new RuntimeException("not found"));
        if(!post.getId().equals(postId)){
            throw new RuntimeException("post ids do not match");
        }
        commentRepository.deleteById(id);
    }
}
