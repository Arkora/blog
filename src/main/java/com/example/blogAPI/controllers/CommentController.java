package com.example.blogAPI.controllers;

import com.example.blogAPI.dtos.commentDto.CommentRequest;
import com.example.blogAPI.dtos.response.MessageResponse;
import com.example.blogAPI.services.comment.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/comments")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class CommentController {

    @Autowired
   private CommentServiceImpl commentService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest){
        commentService.saveComment(commentRequest);
        return  ResponseEntity.ok().body(new MessageResponse("Comment created successfully"));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return ResponseEntity.ok().body(new MessageResponse("Comment deleted successfully"));
    }
}
