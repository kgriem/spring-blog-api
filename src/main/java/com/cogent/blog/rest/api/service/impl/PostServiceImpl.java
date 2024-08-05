package com.cogent.blog.rest.api.service.impl;

import com.cogent.blog.rest.api.entity.Post;
import com.cogent.blog.rest.api.exception.ResourceNotFoundException;
import com.cogent.blog.rest.api.payload.PostResponse;
import com.cogent.blog.rest.api.repository.PostRepository;
import com.cogent.blog.rest.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> postList = posts.getContent();
        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postList);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());

        return postResponse;
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
