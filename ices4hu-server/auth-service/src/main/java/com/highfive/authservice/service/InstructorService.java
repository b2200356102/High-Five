package com.highfive.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Instructor;
import com.highfive.authservice.entity.QInstructor;
import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.DepartmentDTO;
import com.highfive.authservice.entity.dto.InstructorDTO;
import com.highfive.authservice.repository.InstructorRepository;
import com.highfive.authservice.utils.exception.DepartmentNotFoundException;
import com.highfive.authservice.utils.exception.InstructorNotFoundException;
import com.highfive.authservice.utils.exception.UserNotFoundException;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class InstructorService {

	@Autowired
	private InstructorRepository repository;
	private QInstructor instructor = QInstructor.instructor;

	@Autowired
	private UserService userService;
	private QUser user = QUser.user;

	@Autowired
	private DepartmentService departmentService;

	@PersistenceContext
	EntityManager em;

	@Transactional
	public Instructor addInstructor(User user, Integer departmentId) {
		userService.addUser(user);
		Instructor newInstructor = new Instructor(null, user.getId(), departmentId, 100.0,
				false);
		return repository.save(newInstructor);
	}

	public Instructor getInstructor(Integer id) throws InstructorNotFoundException {
		Instructor instructor = repository.findById(id).orElse(null);
		if (instructor == null)
			throw new InstructorNotFoundException();
		return instructor;
	}

	public InstructorDTO getInstructorDTO(String userId)
			throws InstructorNotFoundException, UserNotFoundException {
		Instructor instructor = getInstructorByUserId(userId);
		if (instructor == null)
			throw new InstructorNotFoundException();
		return new InstructorDTO(instructor.getId(), userService.getUser(userId),
				instructor.getDepartmentId(), instructor.getScore(),
				instructor.getIsDepartmentManager());
	}

	public Instructor getInstructorByUserId(String userId) {
		JPAQuery<Instructor> query = new JPAQuery<>(em);
		return query.select(instructor).from(instructor).innerJoin(user)
				.on(instructor.userId.eq(user.id).and(user.id.eq(userId))).fetchFirst();
	}

	@Transactional
	public Instructor setInstructor(InstructorDTO instructorDTO)
			throws UserNotFoundException, DepartmentNotFoundException {
		Instructor newInstructor = repository.findById(instructorDTO.getInstructorId())
				.orElse(null);
		if (newInstructor == null)
			throw new UserNotFoundException();

		userService.setUser(instructorDTO.getUser());

		if (instructorDTO.getDepartmentId() != null)
			newInstructor.setDepartmentId(instructorDTO.getDepartmentId());
		if (instructorDTO.getIsDepartmentManager() != null) {
			if (instructorDTO.getIsDepartmentManager()) {
				newInstructor
						.setIsDepartmentManager(instructorDTO.getIsDepartmentManager());
				Instructor oldDepartmentManager = getInstructorByUserId(
						departmentService.getDepartment(newInstructor.getDepartmentId())
								.getDepartmentManagerId());
				InstructorDTO oldDepartmentManagerDTO = new InstructorDTO(
						oldDepartmentManager.getId(),
						userService.getUser(oldDepartmentManager.getUserId()), null, null,
						false);
				setInstructor(oldDepartmentManagerDTO);

				DepartmentDTO departmentDTO = new DepartmentDTO(
						instructorDTO.getDepartmentId(), null, instructorDTO);
				departmentService.setDepartment(departmentDTO);
			} else {
				newInstructor
						.setIsDepartmentManager(instructorDTO.getIsDepartmentManager());
			}
		}

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
