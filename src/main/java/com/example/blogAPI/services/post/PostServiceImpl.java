package com.example.blogAPI.services.post;

import com.example.blogAPI.dtos.postDto.PostDTO;
import com.example.blogAPI.dtos.postDto.PostRequest;
import com.example.blogAPI.exceptions.ResourceNotFoundException;
import com.example.blogAPI.models.Post;
import com.example.blogAPI.models.User;
import com.example.blogAPI.repositories.PostRepository;
import com.example.blogAPI.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService{


    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    private PostDTO convertEntityToPostDto(Post post){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        PostDTO postDTO = modelMapper.map(post,PostDTO.class);
        return postDTO;
    }

    @Override
    public void savePost(PostRequest postRequest) {
        User user = userRepository.findById(postRequest.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not exists"));

        if (postRequest.getTitle().isEmpty()){
            throw new RuntimeException("Title cannot be empty");
        } else if (postRequest.getBody().isEmpty()) {
            throw new RuntimeException("Body of post cannot be empty");
        }

        Post post = new Post(postRequest.getTitle(), postRequest.getBody(), new Date(),user);
        post.setCreatedAt(new Date());
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        boolean exists = postRepository.existsById(id);
        if (!exists){
            throw new ResourceNotFoundException("Post not exists");
        }
        postRepository.deleteById(id);
    }

    @Override
    public void updatePost(Long id, Map<Object, Object> fields) {
        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Post not found")
                );
        fields.forEach((key,value) ->{
            Field field = ReflectionUtils.findField(Post.class,(String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field,post,value);
        });
    }


    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post not found")
                );

        return convertEntityToPostDto(post);
    }
}
