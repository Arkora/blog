package com.example.blogAPI.controllers;

import com.example.blogAPI.dtos.userDto.UserDetailsDTO;
import com.example.blogAPI.models.User;
import com.example.blogAPI.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private UserDetailsDTO convertEntityToDto(User user){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDetailsDTO userDetailsDTO = modelMapper.map(user,UserDetailsDTO.class);
        return userDetailsDTO;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/register")
    public void addUser(@RequestBody  User user) {
        userService.registerUser(user);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDetailsDTO>> getUsers(){
        return  ResponseEntity.ok(userService.findUsers());
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable Long id){

        return ResponseEntity.ok(convertEntityToDto(userService.findUser(id)));
    }

    @PatchMapping(value = "user/update/{id}")
    public ResponseEntity<UserDetailsDTO> updateUser(@PathVariable Long id, @RequestBody  Map<Object,Object> fields){
        return ResponseEntity.ok(convertEntityToDto(userService.updateUser(id,fields)));
    }

    @DeleteMapping(value = "/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        User user = userService.deleteUser(id);
        return ResponseEntity.ok("User "+user.getFirstName()+" deleted");
    }



}
