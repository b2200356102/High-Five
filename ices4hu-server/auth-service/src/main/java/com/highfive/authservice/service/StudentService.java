package com.highfive.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Student;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.StudentDTO;
import com.highfive.authservice.repository.StudentRepository;
import com.highfive.authservice.utils.exception.UserNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	@Autowired
	private UserService userService;

	@Transactional
	public Student addStudent(User user, Integer departmentId) {
		userService.addUser(user);
		Student newStudent = new Student(null, user.getId(), departmentId, (short) 1,
				true, false);
		return repository.save(newStudent);
	}

	@Transactional
	public Student setStudent(StudentDTO studentDTO) throws UserNotFoundException {
		Student newStudent = repository.findById(studentDTO.getStudentId()).orElse(null);
		if (newStudent == null)
			throw new UserNotFoundException();

		User user = userService.setUser(studentDTO.getUser());
		studentDTO.setUser(user);

		if (studentDTO.getSemester() != null)
			newStudent.setSemester(studentDTO.getSemester());
		if (studentDTO.getDepartment() != null)
			newStudent.setDepartmentId(studentDTO.getDepartment().getDepartmentId());
		if (studentDTO.getIsUndergrad() != null)
			newStudent.setIsUndergrad(studentDTO.getIsUndergrad());
		if (studentDTO.getIsBanned() != null)
			newStudent.setIsBanned(studentDTO.getIsBanned());

		return repository.save(newStudent);
	}

}
