package com.projects.blogapplication.services;

import com.projects.blogapplication.payloads.PostDTO;
import com.projects.blogapplication.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
    PostDTO updatePost(PostDTO postDTO, Integer postId);
    void deletePost(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    PostDTO getPostById(Integer postId);

    List<PostDTO> getPostByCategory(Integer categoryId);
    List<PostDTO> getPostByUser(Integer userId);

    List<PostDTO> searchPost(String keyword);
}
