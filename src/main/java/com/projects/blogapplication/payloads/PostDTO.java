package com.projects.blogapplication.payloads;

import com.projects.blogapplication.models.Category;
import com.projects.blogapplication.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private Category category;
    private Set<CommentDTO> comments = new HashSet<>();

    private User user;

}
