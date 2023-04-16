package com.highfive.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.authservice.model.User;
import com.highfive.authservice.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@PostMapping("/add_user")
	public User addUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@PostMapping("/add_users")
	public List<User> addUsers(@RequestBody List<User> user) {
		return service.saveUsers(user);
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return service.getUsers();
	}

	@PutMapping("/update_user")
	public User updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}

	@DeleteMapping("/delete_user/{id}")
	public String deleteUser(@PathVariable Long id) {
		return service.deleteUserById(id);
	}

}
