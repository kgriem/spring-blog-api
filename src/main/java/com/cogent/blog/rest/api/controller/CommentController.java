package com.cogent.blog.rest.api.controller;

import com.cogent.blog.rest.api.entity.Comment;
import com.cogent.blog.rest.api.entity.Post;
import com.cogent.blog.rest.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/posts/")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable("postId") Long postId, @RequestBody Comment comment){
        Comment data = commentService.createComment(postId, comment);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable("postId") Long postId){
        List<Comment> data = commentService.getCommentsByPost(postId);
        return new ResponseEntity<>(data, HttpStatus.FOUND);
    }

    @GetMapping("/{postId}/comments/{id}")
    public ResponseEntity<Comment> getCommentsById(@PathVariable("postId") Long postId, @PathVariable("id") Long id){
        Comment data = commentService.getCommentById(postId, id);
        return new ResponseEntity<>(data, HttpStatus.FOUND);
    }

    @PutMapping("/{postId}/comments/{id}")
    public ResponseEntity<Comment> updatePost(@PathVariable("postId") Long postId, @PathVariable("id") Long id, @RequestBody Comment comment){
        Comment data = commentService.updateComment(postId, id, comment);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{id}")
    public ResponseEntity deleteComment(@PathVariable("postid") Long postId, @PathVariable("id") Long id){
        commentService.deleteComment(postId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
