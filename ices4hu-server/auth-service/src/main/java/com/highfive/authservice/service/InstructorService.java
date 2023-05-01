package com.highfive.authservice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Department;
import com.highfive.authservice.entity.Instructor;
import com.highfive.authservice.entity.QDepartment;
import com.highfive.authservice.entity.QInstructor;
import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.InstructorDTO;
import com.highfive.authservice.entity.dto.QInstructorDTO;
import com.highfive.authservice.entity.dto.QUserDTO;
import com.highfive.authservice.entity.dto.UserDTO;
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

	@Inject
	@Lazy
	private DepartmentService departmentService;
	private QDepartment department = QDepartment.department;

	@Autowired
	private DepartmentManagerService departmentManagerService;

	@PersistenceContext
	EntityManager em;

	@Transactional
	public Instructor addInstructor(User user, Integer departmentId) {
		userService.addUser(user);
		Instructor newInstructor = new Instructor(user.getId(), departmentId, 100.0);
		return repository.save(newInstructor);
	}

	public List<Instructor> getInstructors() {
		return repository.findAll();
	}

	public List<InstructorDTO> getInstructorDTOs() {

		JPAQuery<UserDTO> userQuery = new JPAQuery<>(em);
		userQuery = userQuery
				.select(new QUserDTO(user.id, user.name, user.mail, user.pending))
				.from(user);

		JPAQuery<InstructorDTO> query = new JPAQuery<>(em);

		return query.select(new QInstructorDTO(userQuery, department, instructor.score))
				.from(instructor).innerJoin(user).on(instructor.userId.eq(user.id))
				.innerJoin(department).on(instructor.departmentId.eq(department.id))
				.fetch();

	}

	public Instructor getInstructorById(String userId)
			throws InstructorNotFoundException {
		JPAQuery<Instructor> query = new JPAQuery<>(em);
		Instructor instructorResponse = query.select(instructor).from(instructor)
				.innerJoin(user).on(instructor.userId.eq(user.id).and(user.id.eq(userId)))
				.fetchFirst();
		if (instructorResponse == null)
			throw new InstructorNotFoundException();
		return instructorResponse;
	}

	public InstructorDTO getInstructorDTOById(String userId)
			throws InstructorNotFoundException, UserNotFoundException,
			DepartmentNotFoundException {
		User user = userService.getUserById(userId);
		if (user == null)
			throw new UserNotFoundException();
		Instructor instructor = getInstructorById(userId);
		if (instructor == null)
			throw new InstructorNotFoundException();
		Department department = departmentService
				.getDepartmentById(instructor.getDepartmentId());
		if (department == null)
			throw new DepartmentNotFoundException();

		return new InstructorDTO(user.toUserDTO(), department, instructor.getScore());
	}

	@Transactional
	public Instructor setInstructor(InstructorDTO instructorDTO)
			throws UserNotFoundException, InstructorNotFoundException,
			DepartmentNotFoundException {
		Instructor newInstructor = getInstructorById(instructorDTO.getUserDTO().getId());

		userService.setUser(instructorDTO.getUserDTO());

		if (instructorDTO.getDepartment() != null)
			newInstructor.setDepartmentId(instructorDTO.getDepartment().getId());
		if (instructorDTO.getScore() != null)
			newInstructor.setScore(instructorDTO.getScore());

		return repository.save(newInstructor);
	}

	@Transactional
	public void removeInstructorById(String id)
			throws InstructorNotFoundException, DepartmentNotFoundException {

		departmentManagerService.removeDepartmentManager(id);
		repository.deleteById(id);
		userService.removeUserById(getInstructorById(id).getUserId());
	}

}
