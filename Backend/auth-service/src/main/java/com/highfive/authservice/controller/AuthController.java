package com.highfive.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.highfive.authservice.entity.Department;
import com.highfive.authservice.entity.DepartmentManager;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.InstructorDTO;
import com.highfive.authservice.entity.dto.StudentDTO;
import com.highfive.authservice.entity.dto.UserDTO;
import com.highfive.authservice.service.AdminService;
import com.highfive.authservice.service.DepartmentManagerService;
import com.highfive.authservice.service.DepartmentService;
import com.highfive.authservice.service.InstructorService;
import com.highfive.authservice.service.StudentService;
import com.highfive.authservice.service.UserService;
import com.highfive.authservice.utils.exception.DepartmentNotFoundException;
import com.highfive.authservice.utils.exception.UserAlreadyExistsException;
import com.highfive.authservice.utils.exception.UserNotFoundException;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@EnableTransactionManagement
public class AuthController {

	@Autowired
	AdminService adminService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	DepartmentManagerService departmentManagerService;

	@Autowired
	InstructorService instructorService;

	@Autowired
	StudentService studentService;

	@Autowired
	UserService userService;

	@PostMapping("/api/departments/")
	public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
		return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.OK);
	}

	@PostMapping("/api/users/{departmentId}/")
	public ResponseEntity<Object> createUser(@RequestBody @Valid User user,
			@PathVariable(name = "departmentId", required = false) Integer departmentId)
			throws UserAlreadyExistsException {
		return new ResponseEntity<>(userService.addUser(user, departmentId), HttpStatus.OK);
	}

	@GetMapping("api/admins/")
	public ResponseEntity<Object> readAdmins() {
		return new ResponseEntity<>(adminService.getAdmins(), HttpStatus.OK);
	}

	@GetMapping("api/departments/")
	public ResponseEntity<Object> readDepartments(
			@RequestParam(name = "departmentName", required = false) String deparmentName)
			throws DepartmentNotFoundException {

		if (deparmentName == null)
			return new ResponseEntity<>(departmentService.getDepartments(), HttpStatus.OK);
		else
			return new ResponseEntity<>(departmentService.getDepartmentByName(deparmentName),
					HttpStatus.OK);
	}

	@GetMapping("api/department_managers/")
	public ResponseEntity<Object> readDepartmentManagers(
			@RequestParam(name = "departmentId", required = false) Integer departmentId)
			throws DepartmentNotFoundException {

		if (departmentId == null)
			return new ResponseEntity<>(departmentManagerService.getDepartmentManagers(),
					HttpStatus.OK);
		else
			return new ResponseEntity<>(
					departmentManagerService.getDepartmentManagerByDepartmentId(departmentId),
					HttpStatus.OK);
	}

	@GetMapping("api/instructors/")
	public ResponseEntity<Object> readInstructors(
			@RequestParam(name = "userId", required = false) String userId)
			throws UserNotFoundException, DepartmentNotFoundException {

		if (userId == null)
			return new ResponseEntity<>(instructorService.getInstructorDTOs(), HttpStatus.OK);
		else
			return new ResponseEntity<>(instructorService.getInstructorDTOById(userId),
					HttpStatus.OK);
	}

	@GetMapping("api/students/")
	public ResponseEntity<Object> readStudents(
			@RequestParam(name = "userId", required = false) String userId)
			throws UserNotFoundException, DepartmentNotFoundException {

		if (userId == null)
			return new ResponseEntity<>(studentService.getStudentDTOs(), HttpStatus.OK);
		else
			return new ResponseEntity<>(studentService.getStudentDTOById(userId), HttpStatus.OK);
	}

	@GetMapping("api/users/")
	public ResponseEntity<Object> readUsers(
			@RequestParam(name = "userId", required = false) String userId)
			throws UserNotFoundException, DepartmentNotFoundException {

		if (userId == null)
			return new ResponseEntity<>(userService.getUserDTOs(), HttpStatus.OK);
		else
			return new ResponseEntity<>(userService.getUserDTOById(userId), HttpStatus.OK);
	}

	@GetMapping("api/psw/")
	public ResponseEntity<Object> checkPassword(@RequestParam(name = "userId") String userId,
			@RequestParam(name = "password") String password)
			throws UserNotFoundException, DepartmentNotFoundException {
		return new ResponseEntity<>(userService.checkPassword(userId, password), HttpStatus.OK);
	}

	@PutMapping("api/departments/")
	public ResponseEntity<Department> updateDepartment(@RequestBody Department department)
			throws DepartmentNotFoundException {
		return new ResponseEntity<>(departmentService.setDepartment(department), HttpStatus.OK);
	}

	@PutMapping("api/users/{role}/")
	public ResponseEntity<String> updateUsers(@PathVariable(name = "role") String role,
			@RequestBody String request) throws UserNotFoundException, DepartmentNotFoundException,
			JsonMappingException, JsonProcessingException {

		ObjectMapper om = new ObjectMapper();

		switch (role) {
		case "department_manager":
			DepartmentManager dm = om.readValue(request, new TypeReference<DepartmentManager>() {
			});
			departmentManagerService.setDepartmentManager(dm.getDepartmentId(),
					dm.getInstructorId());
			break;
		case "instructor":
			InstructorDTO i = om.readValue(request, new TypeReference<InstructorDTO>() {
			});
			instructorService.setInstructor(i);
			break;
		case "student":
			StudentDTO s = om.readValue(request, new TypeReference<StudentDTO>() {
			});
			studentService.setStudent(s);
			break;
		default:
			UserDTO u = om.readValue(request, new TypeReference<UserDTO>() {
			});
			userService.setUser(u);
		}

		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@PutMapping("api/psw/{userId}/")
	public ResponseEntity<String> updatePassword(@PathVariable(name = "userId") String userId,
			@RequestParam(name = "password", required = false) String password)
			throws UserNotFoundException {

		if (password == null)
			userService.resetUserPassword(userId);
		else
			userService.setPassword(userId, password);
		return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
	}

	@DeleteMapping("api/departments/{departmentId}/")
	public ResponseEntity<Object> deleteDepartment(
			@PathVariable(name = "departmentId") Integer departmentId)
			throws DepartmentNotFoundException {
		departmentService.removeDepartment(departmentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("api/users/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable(name = "userId") String userId)
			throws UserNotFoundException, DepartmentNotFoundException {
		userService.removeUserById(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
