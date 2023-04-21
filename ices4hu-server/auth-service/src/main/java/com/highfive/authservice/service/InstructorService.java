package com.highfive.authservice.service;

import org.springframework.stereotype.Service;

@Service
public class InstructorService {

//	@Autowired
//	UserService userService;
//	QUser user = QUser.user;
//
//	@Autowired
//	DepartmentService departmentService;
//	QDepartment department = QDepartment.department;
//
//	@Autowired
//	InstructorRepository repository;
//	QInstructor instructor = QInstructor.ınstructor;
//
//	@PersistenceContext
//	EntityManager em;
//
//	@Transactional
//	public Instructor addInstructor(User user) {
//		userService.createUser(user);
//		// Instructor newInstructor = new Instructor(null, user.getId(), null, 100.0,
//		// false);
//		return null; // newInstructor;
//	}
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
