package com.cogent.blog.rest.api.service.impl;

import com.cogent.blog.rest.api.entity.Post;
import com.cogent.blog.rest.api.exception.ResourceNotFoundException;
import com.cogent.blog.rest.api.repository.PostRepository;
import com.cogent.blog.rest.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;


    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "postId", id));
    }

    @Override
    public Post updatePost(Long id, Post updatePost) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "postId", id));
        post.setTitle(updatePost.getTitle());
        post.setContent(updatePost.getContent());
        post.setDescription(updatePost.getDescription());
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
