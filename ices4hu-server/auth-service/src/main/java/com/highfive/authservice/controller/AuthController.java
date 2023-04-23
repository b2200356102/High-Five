package com.highfive.authservice.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.authservice.entity.Admin;
import com.highfive.authservice.entity.Department;
import com.highfive.authservice.entity.Instructor;
import com.highfive.authservice.entity.Student;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.AdminDTO;
import com.highfive.authservice.entity.dto.DepartmentDTO;
import com.highfive.authservice.entity.dto.InstructorDTO;
import com.highfive.authservice.entity.dto.StudentDTO;
import com.highfive.authservice.service.AdminService;
import com.highfive.authservice.service.DepartmentService;
import com.highfive.authservice.service.InstructorService;
import com.highfive.authservice.service.StudentService;
import com.highfive.authservice.service.UserService;
import com.highfive.authservice.utils.CustomResponseEntity;
import com.highfive.authservice.utils.exception.AdminNotFoundException;
import com.highfive.authservice.utils.exception.DepartmentNotFoundException;
import com.highfive.authservice.utils.exception.InstructorNotFoundException;
import com.highfive.authservice.utils.exception.StudentNotFoundException;
import com.highfive.authservice.utils.exception.UserNotFoundException;

import jakarta.validation.Valid;

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

	@PostMapping("api/instructors/{departmentId}/")
	public CustomResponseEntity<Instructor> createInstructor(
			@RequestBody @Valid User user,
			@PathVariable(name = "departmentId") Integer departmentId) {
		return new CustomResponseEntity<>(
				instructorService.addInstructor(user, departmentId), HttpStatus.OK);
	}

	@PostMapping("api/students/{departmentId}/")
	public CustomResponseEntity<Student> createStudent(@RequestBody @Valid User user,
			@PathVariable(name = "departmentId") Integer departmentId) {
		return new CustomResponseEntity<>(studentService.addStudent(user, departmentId),
				HttpStatus.OK);
	}

	@PostMapping("api/users/")
	public CustomResponseEntity<User> createUser(@RequestBody @Valid User user) {
		return new CustomResponseEntity<>(userService.addUser(user), HttpStatus.OK);
	}

	@GetMapping("api/admins/")
	public CustomResponseEntity<Object> readAdmins(
			@RequestParam(name = "userId", required = false) String userId)
			throws AdminNotFoundException {

		if (userId == null)
			return new CustomResponseEntity<>(adminService.getAdminDTOs(), HttpStatus.OK);
		else
			return new CustomResponseEntity<>(adminService.getAdminByUserId(userId),
					HttpStatus.OK);
	}

	@GetMapping("api/departments/")
	public CustomResponseEntity<Object> readDepartments(
			@RequestParam(name = "departmentId", required = false) Integer departmentId)
			throws DepartmentNotFoundException {

		if (departmentId == null)
			return new CustomResponseEntity<>(departmentService.getDepartmentDTOs(),
					HttpStatus.OK);
		else
			return new CustomResponseEntity<>(
					departmentService.getDepartmentById(departmentId), HttpStatus.OK);
	}

	@GetMapping("api/instructors/")
	public CustomResponseEntity<Object> readInstructors(
			@RequestParam(name = "userId", required = false) String userId)
			throws InstructorNotFoundException, UserNotFoundException {

		if (userId == null)
			return new CustomResponseEntity<>(instructorService.getInstructorDTOs(),
					HttpStatus.OK);
		else
			return new CustomResponseEntity<>(
					instructorService.getInstructorDTOByUserId(userId), HttpStatus.OK);
	}

	@GetMapping("api/students/")
	public CustomResponseEntity<Object> readStudents(
			@RequestParam(name = "userId", required = false) String userId)
			throws StudentNotFoundException, UserNotFoundException {

		if (userId == null)
			return new CustomResponseEntity<>(studentService.getStudentDTOs(),
					HttpStatus.OK);
		else
			return new CustomResponseEntity<>(studentService.getStudentByUserId(userId),
					HttpStatus.OK);
	}

	@GetMapping("api/users/")
	public CustomResponseEntity<Object> readUsers(
			@RequestParam(name = "userId", required = false) String userId)
			throws UserNotFoundException {

		if (userId == null)
			return new CustomResponseEntity<>(userService.getUsers(), HttpStatus.OK);
		else
			return new CustomResponseEntity<>(userService.getUserById(userId),
					HttpStatus.OK);
	}

	@GetMapping("api/psw/{userId}/")
	public CustomResponseEntity<String> readUserPassword(
			@PathVariable(name = "userId") String userId) throws UserNotFoundException {
		return new CustomResponseEntity<String>(userService.getUserPasswordById(userId),
				HttpStatus.OK);
	}

	@PutMapping("api/admins/")
	public CustomResponseEntity<Admin> updateAdmin(@RequestBody AdminDTO adminDTO)
			throws UserNotFoundException {
		return new CustomResponseEntity<>(adminService.setAdmin(adminDTO), HttpStatus.OK);
	}

	@PutMapping("api/departments/")
	public CustomResponseEntity<Department> updateDepartment(
			@RequestBody DepartmentDTO departmentDTO) throws DepartmentNotFoundException {
		return new CustomResponseEntity<>(departmentService.setDepartment(departmentDTO),
				HttpStatus.OK);
	}

	@PutMapping("api/instructors/")
	public CustomResponseEntity<Instructor> updateInstructor(
			@RequestBody InstructorDTO instructorDTO) throws UserNotFoundException,
			InstructorNotFoundException, DepartmentNotFoundException {
		return new CustomResponseEntity<>(instructorService.setInstructor(instructorDTO),
				HttpStatus.OK);
	}

	@PutMapping("api/students/")
	public CustomResponseEntity<Student> updateStudent(@RequestBody StudentDTO studentDTO)
			throws UserNotFoundException {
		return new CustomResponseEntity<>(studentService.setStudent(studentDTO),
				HttpStatus.OK);
	}

	@PutMapping("api/users/")
	public CustomResponseEntity<User> updateUser(@RequestBody User user)
			throws UserNotFoundException {
		return new CustomResponseEntity<>(userService.setUser(user), HttpStatus.OK);
	}

	@PutMapping("api/psw/{userId}/")
	public CustomResponseEntity<String> updateUserPassword(
			@PathVariable(name = "userId") String userId) throws UserNotFoundException {

		userService.setUserPassword(userId);
		return new CustomResponseEntity<>("Password changed successfully", HttpStatus.OK);
	}

	@DeleteMapping("api/admins/{userId}")
	public ResponseEntity<Object> deleteAdmin(
			@PathVariable(name = "userId") String userId) throws AdminNotFoundException {
		adminService.removeAdminByUserId(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("api/departments/{departmentId}/")
	public ResponseEntity<Object> deleteDepartment(
			@PathVariable(name = "departmentId") Integer departmentId)
			throws StudentNotFoundException, InstructorNotFoundException,
			DepartmentNotFoundException {
		departmentService.removeDepartment(departmentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("api/instructors/{userId}")
	public ResponseEntity<Object> deleteInstructor(
			@PathVariable(name = "userId") String userId)
			throws InstructorNotFoundException, DepartmentNotFoundException {
		instructorService.removeInstructorByUserId(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("api/students/{userId}")
	public ResponseEntity<Object> deleteStudent(
			@PathVariable(name = "userId") String userId)
			throws StudentNotFoundException {
		studentService.removeStudentByUserId(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("api/users/{userId}")
	public ResponseEntity<Object> deleteUser(
			@PathVariable(name = "userId") String userId) {
		userService.removeUserById(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
