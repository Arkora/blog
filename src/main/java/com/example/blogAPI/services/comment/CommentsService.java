package com.example.blogAPI.services.comment;

import com.example.blogAPI.dtos.commentDto.CommentRequest;

public interface CommentsService {
    void saveComment(CommentRequest commentRequest);
    void deleteComment(Long id);
    void updateComment(CommentRequest commentRequest);
}
