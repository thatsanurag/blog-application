package com.projects.blogapplication.repositories;

import com.projects.blogapplication.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
