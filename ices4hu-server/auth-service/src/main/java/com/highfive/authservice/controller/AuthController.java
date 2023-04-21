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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.authservice.entity.Admin;
import com.highfive.authservice.entity.Department;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.QUserDTO;
import com.highfive.authservice.entity.dto.UserDTO;
import com.highfive.authservice.service.AdminService;
import com.highfive.authservice.service.DepartmentService;
import com.highfive.authservice.service.UserService;
import com.highfive.authservice.utils.CustomResponseEntity;

@RestController
@EnableTransactionManagement
public class AuthController {

	@Autowired
	UserService userService;

	@Autowired
	AdminService adminService;

	@Autowired
	DepartmentService departmentService;

	@PostMapping("api/users/admins/")
	public CustomResponseEntity<Admin> createAdmin(@RequestBody User user) {
		return new CustomResponseEntity<>(adminService.addAdmin(user), HttpStatus.OK);
	}

	@GetMapping("api/users/admins/")
	public CustomResponseEntity<List<Admin>> readAdmins() {
		return new CustomResponseEntity<>(adminService.getAdmins(), HttpStatus.OK);
	}

	@DeleteMapping("api/users/admins/{id}/")
	public CustomResponseEntity<UserDTO> deleteAdmin(@PathVariable(name = "id") String userId) {
		return new CustomResponseEntity<>(adminService.deleteAdmin(userId), HttpStatus.OK);
	}

	@PostMapping("api/departments/")
	public CustomResponseEntity<Department> createDepartment(@RequestBody Department department) {
		return new CustomResponseEntity<>(departmentService.addDepartment(department), HttpStatus.OK);
	}

	@GetMapping("api/departments/")
	public CustomResponseEntity<List<Department>> readDepartments() {
		return new CustomResponseEntity<>(departmentService.getDepartments(), HttpStatus.OK);
	}

	@GetMapping("api/departments/{id}")
	public CustomResponseEntity<Department> readDepartment(@PathVariable(name = "id") Integer departmentId) {
		return new CustomResponseEntity<>(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
	}

	@GetMapping("api/departments/{id}/department_manager")
	public CustomResponseEntity<UserDTO> readDepartmentManager(@PathVariable(name = "id") Integer deparmentId) {
		return new CustomResponseEntity<>(departmentService.getDepartmentManager(deparmentId), HttpStatus.OK);
	}

	@PutMapping("api/departments/{id}/{userId}")
	public CustomResponseEntity<Department> updateDepartmentManager(@PathVariable(name = "id") Integer departmentId,
			@PathVariable(name = "userId") String userId) {
		return new CustomResponseEntity<>(departmentService.setDepartmentManager(departmentId, userId), HttpStatus.OK);
	}

	@DeleteMapping("api/departments/{id}")
	public ResponseEntity<Object> deleteDepartment(@PathVariable(name = "id") Integer id) {
		departmentService.deleteDepartment(id);
		return new ResponseEntity<>(HttpStatus.OK);
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
