package com.example.blogAPI.controllers;

import com.example.blogAPI.dtos.response.MessageResponse;
import com.example.blogAPI.dtos.userDto.LoginDTO;
import com.example.blogAPI.dtos.userDto.SignupDTO;
import com.example.blogAPI.dtos.userDto.UserDetailsDTO;
import com.example.blogAPI.models.User;
import com.example.blogAPI.security.jwt.JwtUtils;
import com.example.blogAPI.services.user.UserDetailsImpl;
import com.example.blogAPI.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class AuthController {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {


        UserDetailsImpl userDetails = userServiceImpl.getUserByUsername(loginDTO.getUsername(), loginDTO.getPassword());

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        String token = jwtCookie.toString().split(";")[0].split("=")[1];


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserDetailsDTO(userDetails.getId(),
                        userDetails.getFirstname(),
                        userDetails.getLastname(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        token,
                        userDetails.getCreatedAt()));

    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDTO signupDTO){
        User user = new User(signupDTO.getFirstname(),signupDTO.getLastname(),signupDTO.getEmail(),signupDTO.getUsername(),signupDTO.getPassword());

        userServiceImpl.saveUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



}
