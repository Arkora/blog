package com.example.blogAPI.services.comment;

import com.example.blogAPI.dtos.commentDto.CommentRequest;
import com.example.blogAPI.exceptions.ResourceNotFoundException;
import com.example.blogAPI.models.Comment;
import com.example.blogAPI.models.Post;
import com.example.blogAPI.models.User;
import com.example.blogAPI.repositories.CommentRepository;
import com.example.blogAPI.repositories.PostRepository;
import com.example.blogAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentsService{

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveComment(CommentRequest commentRequest) {
        Post post = postRepository.findById(commentRequest.getPostId())
                .orElseThrow(()->new ResourceNotFoundException("No post found"));
        User user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() ->new ResourceNotFoundException("User not found"));
        Comment comment = new Comment(commentRequest.getBody(),new Date(),post,user);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void updateComment(CommentRequest commentRequest) {

    }
}
