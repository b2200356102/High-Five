package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Instructor;
import com.highfive.authservice.entity.QInstructor;
import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.DepartmentDTO;
import com.highfive.authservice.entity.dto.InstructorDTO;
import com.highfive.authservice.entity.dto.QInstructorDTO;
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

	public List<Instructor> getInstructors() {
		return repository.findAll();
	}

	public List<InstructorDTO> getInstructorDTOs() {
		JPAQuery<InstructorDTO> query = new JPAQuery<>(em);
		return query
				.select(new QInstructorDTO(instructor.id, user, instructor.departmentId,
						instructor.score, instructor.isDepartmentManager))
				.from(instructor).innerJoin(user).on(instructor.userId.eq(user.id))
				.fetch();
	}

	public Instructor getInstructorById(Integer id) throws InstructorNotFoundException {
		Instructor instructor = repository.findById(id).orElse(null);
		if (instructor == null)
			throw new InstructorNotFoundException();
		return instructor;
	}

	public InstructorDTO getInstructorDTOById(Integer id)
			throws InstructorNotFoundException {
		JPAQuery<InstructorDTO> query = new JPAQuery<>(em);
		InstructorDTO instructorDTO = query
				.select(new QInstructorDTO(instructor.id, user, instructor.departmentId,
						instructor.score, instructor.isDepartmentManager))
				.from(instructor).innerJoin(user).on(instructor.userId.eq(user.id))
				.where(instructor.id.eq(id)).fetchFirst();
		if (instructorDTO == null)
			throw new InstructorNotFoundException();
		return instructorDTO;
	}

	public Instructor getInstructorByUserId(String userId)
			throws InstructorNotFoundException {
		JPAQuery<Instructor> query = new JPAQuery<>(em);
		Instructor instructorResponse = query.select(instructor).from(instructor)
				.innerJoin(user).on(instructor.userId.eq(user.id).and(user.id.eq(userId)))
				.fetchFirst();
		if (instructorResponse == null)
			throw new InstructorNotFoundException();
		return instructorResponse;
	}

	public InstructorDTO getInstructorDTOByUserId(String userId)
			throws InstructorNotFoundException, UserNotFoundException {
		Instructor instructor = getInstructorByUserId(userId);
		if (instructor == null)
			throw new InstructorNotFoundException();
		return getInstructorDTOById(instructor.getId());
	}

	@Transactional
	public Instructor setInstructor(InstructorDTO instructorDTO)
			throws UserNotFoundException, InstructorNotFoundException,
			DepartmentNotFoundException {
		Instructor newInstructor = getInstructorByUserId(instructorDTO.getUser().getId());

		userService.setUser(instructorDTO.getUser());

		if (instructorDTO.getDepartmentId() != null)
			newInstructor.setDepartmentId(instructorDTO.getDepartmentId());
		if (instructorDTO.getIsDepartmentManager() != null) {
			if (instructorDTO.getIsDepartmentManager()) {
				newInstructor
						.setIsDepartmentManager(instructorDTO.getIsDepartmentManager());

				Instructor oldDepartmentManager = getInstructorByUserId(departmentService
						.getDepartmentById(newInstructor.getDepartmentId())
						.getDepartmentManagerId());
				InstructorDTO oldDepartmentManagerDTO = new InstructorDTO(
						oldDepartmentManager.getId(),
						userService.getUserById(oldDepartmentManager.getUserId()), null,
						null, false);
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

	@Transactional
	public void removeInstructorById(Integer id) throws InstructorNotFoundException {
		repository.deleteById(id);
		userService.removeUserById(getInstructorById(id).getUserId());
	}

	@Transactional
	public void removeInstructorByUserId(String userId)
			throws InstructorNotFoundException {
		repository.deleteById(getInstructorByUserId(userId).getId());
		userService.removeUserById(userId);
	}

}
