package com.example.blogAPI.services;


import com.example.blogAPI.dtos.userDto.UserDetailsDTO;
import com.example.blogAPI.exceptions.ResourceNotFoundException;
import com.example.blogAPI.models.User;
import com.example.blogAPI.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    private UserDetailsDTO convertEntityToDto(User user){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDetailsDTO userDetailsDTO = modelMapper.map(user,UserDetailsDTO.class);
        return userDetailsDTO;
    }

    public User registerUser(User user)  {

        boolean exist = userRepository.existsByEmail(user.getEmail());

               if (exist){
                   throw new ResourceNotFoundException("Email Already exist");
               }
               user.setCreatedAt(new Date());
               userRepository.save(user);
               return user;

    }

    public List<UserDetailsDTO> findUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public User findUser(Long id){
        return userRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("User not exist"));

    }

    public User deleteUser(Long id) {
       User user = findUser(id);
       userRepository.deleteById(user.getId());
       return user;
    }

    public User updateUser(Long id, Map<Object,Object> fields){
        User user = findUser(id);
        fields.forEach((key,value) ->{
            Field field = ReflectionUtils.findField(User.class,(String) key);
            if (field.getName() == "email"){
                throw new RuntimeException("Email already registered");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field,user,value);
        });
        return userRepository.save(user);
    }


}
