package com.highfive.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.authservice.entity.Admin;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.QUserDTO;
import com.highfive.authservice.entity.dto.UserDTO;
import com.highfive.authservice.service.AdminService;
import com.highfive.authservice.service.UserService;
import com.highfive.authservice.utils.CustomResponseEntity;

@RestController
@EnableTransactionManagement
public class AuthController {

	@Autowired
	UserService userService;

	@Autowired
	AdminService adminService;

	@PostMapping("api/users/admins")
	public CustomResponseEntity<Admin> createAdmin(@RequestBody User user) {
		return new CustomResponseEntity<Admin>(adminService.createAdmin(user), HttpStatus.OK);
	}

	@GetMapping("api/users/admins")
	public CustomResponseEntity<List<Admin>> readAdmins() {
		return new CustomResponseEntity<List<Admin>>(adminService.readAdmins(), HttpStatus.OK);
	}

	@DeleteMapping("api/users/admins/{id}")
	public CustomResponseEntity<UserDTO> deleteAdmin(@PathVariable(name = "id") String userId) {
		return new CustomResponseEntity<UserDTO>(adminService.deleteAdmin(userId), HttpStatus.OK);
	}

	@GetMapping("/api/users")
	public ResponseEntity<List<QUserDTO>> readUsers() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}

	@GetMapping("/api/users/{id}")
	public ResponseEntity<UserDTO> readUserById(@PathVariable String id) {
		return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
	}

}
