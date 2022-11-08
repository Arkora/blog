package com.example.blogAPI.dtos.userDto;

import lombok.Data;

@Data
public class SignupDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
}
