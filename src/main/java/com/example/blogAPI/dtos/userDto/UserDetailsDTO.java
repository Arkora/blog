package com.example.blogAPI.dtos.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserDetailsDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String token;
    private Date createdAt;
    private Collection<String> roles;


    public UserDetailsDTO(Long id, String firstname, String lastname, String username, String email, String token, Date createdAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.token = token;
        this.createdAt = createdAt;
    }
}
