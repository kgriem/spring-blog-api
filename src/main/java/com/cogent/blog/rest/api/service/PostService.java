package com.cogent.blog.rest.api.service;

import com.cogent.blog.rest.api.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Post createPost(Post post);
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post updatePost(Long id, Post updatePost);
    void deletePost(Long id);
}
