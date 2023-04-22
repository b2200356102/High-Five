package com.highfive.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.authservice.entity.Department;
import com.highfive.authservice.entity.Instructor;
import com.highfive.authservice.entity.Student;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.DepartmentDTO;
import com.highfive.authservice.service.AdminService;
import com.highfive.authservice.service.DepartmentService;
import com.highfive.authservice.service.InstructorService;
import com.highfive.authservice.service.StudentService;
import com.highfive.authservice.service.UserService;
import com.highfive.authservice.utils.CustomResponseEntity;

@RestController
@EnableTransactionManagement
public class AuthController {

	@Autowired
	AdminService adminService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	InstructorService instructorService;

	@Autowired
	StudentService studentService;

	@Autowired
	UserService userService;

	@PostMapping("api/departments/")
	public CustomResponseEntity<Department> createDepartment(
			@RequestBody DepartmentDTO departmentDTO) {
		return new CustomResponseEntity<>(departmentService.addDepartment(departmentDTO),
				HttpStatus.OK);
	}

	@PostMapping("api/instructors/{departmentId}")
	public CustomResponseEntity<Instructor> createInstructor(@RequestBody User user,
			@PathVariable(name = "departmentId") Integer departmentId) {
		return new CustomResponseEntity<>(
				instructorService.addInstructor(user, departmentId), HttpStatus.OK);
	}

	@PostMapping("api/students/{departmentId}")
	public CustomResponseEntity<Student> createStudent(@RequestBody User user,
			@PathVariable(name = "departmentId") Integer departmentId) {
		return new CustomResponseEntity<>(studentService.addStudent(user, departmentId),
				HttpStatus.OK);
	}

	@PostMapping("api/users/")
	public CustomResponseEntity<User> createUser(@RequestBody User user) {
		return new CustomResponseEntity<>(userService.addUser(user), HttpStatus.OK);
	}
}
