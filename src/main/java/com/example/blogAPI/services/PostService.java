package com.example.blogAPI.services;

import com.example.blogAPI.models.Post;
import com.example.blogAPI.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post){

        post.setCreatedAt(new Date());
       return postRepository.save(post);
    }

    public List<Post> retrivePosts(){
        return postRepository.findAll();
    }

}
