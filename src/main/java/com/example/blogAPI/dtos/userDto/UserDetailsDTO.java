package com.example.blogAPI.dtos.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserDetailsDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private Collection<String> roles;


}
