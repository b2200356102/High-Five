package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Instructor;
import com.highfive.authservice.entity.QDepartment;
import com.highfive.authservice.entity.QInstructor;
import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.QUserDTO;
import com.highfive.authservice.entity.dto.UserDTO;
import com.highfive.authservice.repository.InstructorRepository;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class InstructorService {

	@Autowired
	UserService userService;
	QUser user = QUser.user;

	@Autowired
	DepartmentService departmentService;
	QDepartment department = QDepartment.department;

	@Autowired
	InstructorRepository repository;
	QInstructor instructor = QInstructor.ınstructor;

	@PersistenceContext
	EntityManager em;

	@Transactional
	public Instructor addInstructor(User user) {
		userService.createUser(user);
		Instructor newInstructor = new Instructor(null, user.getId(), null, 100.0, false);
		return newInstructor;
	}

	public List<Instructor> getInstructors() {
		return repository.findAll();
	}

	public UserDTO getInstructor(String id) {
		JPAQuery<UserDTO> query = new JPAQuery<>(em);
		query.select(new QUserDTO(user.id, user.name, user.surname, user.mail, user.role)).from(user)
				.innerJoin(instructor).on(user.id.eq(instructor.userId));
	}

}
