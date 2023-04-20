package com.highfive.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.QUserDTO;
import com.highfive.authservice.entity.dto.UserDTO;
import com.highfive.authservice.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@PostMapping("/api/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {

		try {
			User newUser = service.createUser(user);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("ERROR", e.getMessage());
			return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/api/users")
	public ResponseEntity<List<QUserDTO>> readUsers() {
		return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
	}

	@GetMapping("/api/users/{id}")
	public ResponseEntity<UserDTO> readUserById(@PathVariable Integer id) {
		return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
	}

}
