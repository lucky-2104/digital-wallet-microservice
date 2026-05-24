package com.digital_wallet.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digital_wallet.user_service.model.User;
import com.digital_wallet.user_service.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
	    this.userService = userService;
	}
	//For creating the user
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		
		User createdUser = userService.registerUser(user);
		
		return createdUser;
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable long id) {
		
		User user = userService.getUserById(id); 
		return user;
	}
	
	@GetMapping
	public List<User> getAllUsers() {
		
		List<User> allUser = userService.getAllUsers(); 
		return allUser;
	}
	
	

}
