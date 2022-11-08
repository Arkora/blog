package com.example.blogAPI.controllers;

import com.example.blogAPI.dtos.response.MessageResponse;
import com.example.blogAPI.dtos.userDto.LoginDTO;
import com.example.blogAPI.dtos.userDto.SignupDTO;
import com.example.blogAPI.dtos.userDto.UserDetailsDTO;
import com.example.blogAPI.dtos.userDto.UserPostsDTO;
import com.example.blogAPI.models.User;
import com.example.blogAPI.services.user.UserDetailsImpl;
import com.example.blogAPI.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping(value = "/{id}")
    @PreAuthorize("(authentication.principal.id == #id) or hasAuthority('ADMIN')")
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable Long id){

        return ResponseEntity.ok(userServiceImpl.getUserById(id));
    }

    @PatchMapping(value = "/update/{id}")
    @PreAuthorize("(authentication.principal.id == #id) or hasAuthority('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody  Map<Object,Object> fields){
        userServiceImpl.updateUser(id, fields);
        return ResponseEntity.ok().body(new MessageResponse("User updated"));
    }
   @DeleteMapping(value = "/delete/{id}")
   @PreAuthorize("(authentication.principal.id == #id) or hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
         userServiceImpl.deleteUser(id);
        return ResponseEntity.ok("User  deleted");
    }

    @GetMapping(value = "/post/{id}")
    @PreAuthorize("(authentication.principal.id == #id) or hasAuthority('ADMIN')")
    public ResponseEntity<UserPostsDTO> getUsersPosts(@PathVariable Long id){
        return ResponseEntity.ok().body(userServiceImpl.getUsersPosts(id));
    }



}
