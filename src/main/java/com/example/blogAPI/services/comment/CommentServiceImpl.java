package com.example.blogAPI.services.comment;

import com.example.blogAPI.dtos.commentDto.CommentRequest;
import com.example.blogAPI.dtos.notificationDto.NotificationPostDTO;
import com.example.blogAPI.exceptions.ResourceNotFoundException;
import com.example.blogAPI.models.Comment;
import com.example.blogAPI.models.Post;
import com.example.blogAPI.models.User;
import com.example.blogAPI.repositories.CommentRepository;
import com.example.blogAPI.repositories.PostRepository;
import com.example.blogAPI.repositories.UserRepository;
import com.example.blogAPI.services.notification.NotificationServiceImpl;
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

    @Autowired
    NotificationServiceImpl notificationService;

    @Override
    public void saveComment(CommentRequest commentRequest) {
        Post post = postRepository.findById(commentRequest.getPostId())
                .orElseThrow(()->new ResourceNotFoundException("No post found"));
        User user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() ->new ResourceNotFoundException("User not found"));
        Comment comment = new Comment(commentRequest.getBody(),new Date(),post,user);
        commentRepository.save(comment);
        User postOwner = post.getUser();
        String body ;
        if (user.equals(postOwner)){
            return;
        }else {
            body = user.getFirstname() + " " + user.getLastname() + " commented at your post " + post.getTitle();
            notificationService.save(new NotificationPostDTO(body,postOwner, post.getId()));
        }


    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void updateComment(CommentRequest commentRequest) {

    }
}
