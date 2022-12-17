package com.example.blogAPI.controllers;

import com.example.blogAPI.dtos.postDto.PostDTO;
import com.example.blogAPI.dtos.response.MessageResponse;
import com.example.blogAPI.dtos.userDto.UserDetailsDTO;
import com.example.blogAPI.dtos.userDto.UserPostsDTO;
import com.example.blogAPI.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
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
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
         userServiceImpl.deleteUser(id);
        return ResponseEntity.ok().body(new MessageResponse("Account Deleted Successfully"));
    }

    @GetMapping(value = "/post/{id}")
    public ResponseEntity<?> getUsersPosts(@PathVariable Long id){
        UserPostsDTO userPostsDTO = userServiceImpl.getUsersPosts(id);
        Collection<PostDTO> posts = new ArrayList();

            userPostsDTO.getPosts().forEach(postDTO -> {
                posts.add(postDTO);
            });


        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/posts")
    public ResponseEntity<?> getUsersPosts(){
        Collection<UserPostsDTO> userPostsDTOS = userServiceImpl.getRandomPosts();
        Collection<PostDTO> posts = new ArrayList();
        userPostsDTOS.forEach(userPostsDTO -> {
            userPostsDTO.getPosts().forEach(postDTO -> {
                posts.add(postDTO);
            });
        });
        return ResponseEntity.ok().body(posts);
    }

}
