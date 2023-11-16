package com.projects.blogapplication.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "User name must be greater than equal to 4 characters.")
    private String name;

    @Email(message = "Email address is invalid.")
    private String email;

    @NotEmpty
    @Size(min = 8, message = "Minimum of 8 characters required.")
    private String password;

    @NotEmpty
    private String about;
}
