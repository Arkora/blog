package com.example.blogAPI.dtos.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PasswordChangeDTO {
    private Long userId;
    private String password;
    private String newPassword;
}
