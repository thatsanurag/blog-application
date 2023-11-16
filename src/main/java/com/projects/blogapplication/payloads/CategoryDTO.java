package com.projects.blogapplication.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private int id;
    @NotBlank
    @Size(min = 4, message = "Title cannot be less than 4 characters.")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, max = 500, message = "Invalid")
    private String categoryDescription;
}
