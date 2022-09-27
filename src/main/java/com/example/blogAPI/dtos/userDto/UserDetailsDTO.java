package com.example.blogAPI.dtos.userDto;

import lombok.Data;

@Data
public class UserDetailsDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;


}
