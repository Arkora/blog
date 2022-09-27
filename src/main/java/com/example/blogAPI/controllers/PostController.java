package com.example.blogAPI.controllers;

import com.example.blogAPI.models.Post;
import com.example.blogAPI.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {


    @Autowired
    private PostService postService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
      return  ResponseEntity.ok(postService.savePost(post));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Post>> getPosts(){
        return ResponseEntity.ok(postService.retrivePosts());
    }
}
