package com.example.blogAPI.controllers;

import com.example.blogAPI.dtos.postDto.PostRequest;
import com.example.blogAPI.dtos.response.MessageResponse;
import com.example.blogAPI.models.Post;
import com.example.blogAPI.services.PostService;
import com.example.blogAPI.services.post.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/posts")
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
        return  ResponseEntity.ok().body(new MessageResponse("Post updated successfully"));
    }


}
