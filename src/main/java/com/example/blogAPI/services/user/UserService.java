package com.example.blogAPI.services.user;

import com.example.blogAPI.dtos.userDto.UserDetailsDTO;
import com.example.blogAPI.dtos.userDto.UserPostsDTO;
import com.example.blogAPI.models.Role;
import com.example.blogAPI.models.User;

import java.util.Map;

public interface UserService {
    void saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    UserDetailsImpl getUserByUsername(String username,String password);
    UserDetailsDTO getUserById(Long id);
    void deleteUser(Long id);

    UserPostsDTO getUsersPosts(Long id);

    void updateUser(Long id, Map<Object,Object> fields);



}