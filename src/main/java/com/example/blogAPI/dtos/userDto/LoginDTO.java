package com.example.blogAPI.dtos.userDto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {
    @NotBlank
    public String username;
    @NotBlank
    public String password;
}
