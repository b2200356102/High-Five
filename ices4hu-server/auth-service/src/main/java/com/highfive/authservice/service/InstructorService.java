package com.highfive.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Instructor;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.repository.InstructorRepository;
import com.highfive.authservice.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class InstructorService {

	@Autowired
	private InstructorRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public Instructor addInstructor(User user, Integer departmentId) {
		userRepository.save(user);
		Instructor newInstructor = new Instructor(null, user.getId(), departmentId, 100.0, false);
		return repository.save(newInstructor);
	}

//	@Autowired
//	UserService userService;
//	QUser user = QUser.user;
//
//	@Autowired
//	DepartmentService departmentService;
//	QDepartment department = QDepartment.department;
//
//	QInstructor instructor = QInstructor.ınstructor;
//
//	@PersistenceContext
//	EntityManager em;
//
//
//	public List<Instructor> getInstructors() {
//		return repository.findAll();
//	}
//
//	public UserDTO getInstructor(String id) {
//		JPAQuery<UserDTO> query = new JPAQuery<>(em);
//		query.select(new QUserDTO(user.id, user.name, user.surname, user.mail, user.role))
//				.from(user).innerJoin(instructor).on(user.id.eq(instructor.userId));
//		return null;
//	}

}
