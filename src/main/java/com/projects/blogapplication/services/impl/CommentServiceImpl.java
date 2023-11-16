package com.projects.blogapplication.services.impl;

import com.projects.blogapplication.Exceptions.ResourceNotFoundException;
import com.projects.blogapplication.models.Comment;
import com.projects.blogapplication.models.Post;
import com.projects.blogapplication.payloads.CommentDTO;
import com.projects.blogapplication.repositories.CommentRepo;
import com.projects.blogapplication.repositories.PostRepo;
import com.projects.blogapplication.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;
    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "PostId", postId));
        Comment comment = this.modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment", "CommentId", commentId));
        this.commentRepo.delete(comment);

    }
}
