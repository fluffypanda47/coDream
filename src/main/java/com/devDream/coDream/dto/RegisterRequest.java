package com.devDream.coDream.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    @NotBlank(message = "Username is Required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;


}
