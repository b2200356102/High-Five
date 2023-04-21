package com.highfive.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Instructor;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.InstructorDTO;
import com.highfive.authservice.repository.InstructorRepository;
import com.highfive.authservice.utils.exception.UserNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class InstructorService {

	@Autowired
	private InstructorRepository repository;

	@Autowired
	private UserService userService;

	@Transactional
	public Instructor addInstructor(User user, Integer departmentId) {
		userService.addUser(user);
		Instructor newInstructor = new Instructor(null, user.getId(), departmentId, 100.0,
				false);
		return repository.save(newInstructor);
	}

	@Transactional
	public Instructor setInstructor(InstructorDTO instructorDTO)
			throws UserNotFoundException {
		Instructor newInstructor = repository.findById(instructorDTO.getInstructorId())
				.orElse(null);
		if (newInstructor == null)
			throw new UserNotFoundException();

		User user = userService.setUser(instructorDTO.getUser());
		instructorDTO.setUser(user);

		if (instructorDTO.getDepartmentId() != null)
			newInstructor.setDepartmentId(instructorDTO.getDepartmentId());
		if (instructorDTO.getIsDepartmentManager() != null)
			newInstructor.setIsDepartmentManager(instructorDTO.getIsDepartmentManager());
		if (instructorDTO.getScore() != null)
			newInstructor.setScore(instructorDTO.getScore());

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
