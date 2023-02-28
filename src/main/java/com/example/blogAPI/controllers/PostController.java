package com.example.blogAPI.controllers;

import com.example.blogAPI.dtos.postDto.PostRequest;
import com.example.blogAPI.dtos.response.MessageResponse;
import com.example.blogAPI.services.post.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/posts")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class PostController {


    @Autowired
    private PostServiceImpl postService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest){
        postService.savePost(postRequest);
      return  ResponseEntity.ok().body(new MessageResponse("Post created successfully"));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getPost(@PathVariable Long id){
        return ResponseEntity.ok().body(postService.getPostById(id));

    }
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return  ResponseEntity.ok().body(new MessageResponse("Post deleted successfully"));
    }

    @PatchMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> updatePost(@PathVariable Long id ,@RequestBody Map<Object,Object> fields){
        postService.updatePost(id, fields);
        System.out.println(fields.keySet());
        return  ResponseEntity.ok().body(new MessageResponse("Post updated successfully"));
    }


    @GetMapping(value = "/profile/{id}")
    public ResponseEntity<?> getUsersPosts(@PathVariable Long id){
        return ResponseEntity.ok().body(postService.getProfilePosts(id));
    }
    @GetMapping(value = "/all")
    public ResponseEntity<?> getRandomPosts(){
        return ResponseEntity.ok().body(postService.getALl());
    }



}
