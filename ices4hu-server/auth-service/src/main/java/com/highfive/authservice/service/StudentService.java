package com.highfive.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Student;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.repository.StudentRepository;
import com.highfive.authservice.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public Student addStudent(User user, Integer departmentId) {
		userRepository.save(user);
		Student newStudent = new Student(null, user.getId(), departmentId, (short) 1, true, false);
		return repository.save(newStudent);
	}

}
