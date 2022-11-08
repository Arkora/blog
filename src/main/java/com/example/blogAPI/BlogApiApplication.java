package com.example.blogAPI;

import com.example.blogAPI.models.Role;
import com.example.blogAPI.services.user.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApiApplication  {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}



	public static void main(String[] args)  {
		SpringApplication.run(BlogApiApplication.class, args);

	}
	@Autowired
	//UserServiceImpl userService;

	@Bean
	CommandLineRunner commandLineRunner(UserServiceImpl userService){
		return  args ->{
			userService.saveRole(new Role("USER"));
			userService.saveRole(new Role("ADMIN"));
		};
	}
}