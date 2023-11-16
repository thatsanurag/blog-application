package com.projects.blogapplication.controller;

import com.projects.blogapplication.payloads.ApiResponse;
import com.projects.blogapplication.payloads.CommentDTO;
import com.projects.blogapplication.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@ResponseBody
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,
                                                    @PathVariable Integer postId) {
        CommentDTO commentDTO1 = this.commentService.createComment(commentDTO, postId);
        return new ResponseEntity<CommentDTO>(commentDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment has been deleted successfully",
                true), HttpStatus.OK);
    }
}
