package com.example.blogAPI.services.post;

import com.example.blogAPI.dtos.postDto.PostDTO;
import com.example.blogAPI.dtos.postDto.PostRequest;
import com.example.blogAPI.models.Post;

import java.util.Collection;
import java.util.Map;

public interface PostService {
    void savePost(PostRequest postRequest);
    void deletePost(Long id);

    void updatePost(Long id, Map<Object,Object> fields);

    Collection<PostDTO> getALl();

    Collection<PostDTO> getProfilePosts(Long id);

    PostDTO getPostById (Long id);

}
