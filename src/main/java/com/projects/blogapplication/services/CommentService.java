package com.projects.blogapplication.services;

import com.projects.blogapplication.payloads.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, Integer postId);
    void deleteComment(Integer commentId);

}
