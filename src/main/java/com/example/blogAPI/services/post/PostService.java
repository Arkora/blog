package com.example.blogAPI.services.post;

import com.example.blogAPI.dtos.postDto.PostDTO;
import com.example.blogAPI.dtos.postDto.PostRequest;

import java.util.Map;

public interface PostService {
    void savePost(PostRequest postRequest);
    void deletePost(Long id);

    void updatePost(Long id, Map<Object,Object> fields);

    PostDTO getPostById (Long id);

}
