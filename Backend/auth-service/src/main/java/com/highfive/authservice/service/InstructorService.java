package com.highfive.authservice.service;

import java.util.List;

import javax.inject.Inject;

import com.highfive.authservice.entity.QLecturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Department;
import com.highfive.authservice.entity.Lecturer;
import com.highfive.authservice.entity.QDepartment;
import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.InstructorDTO;
import com.highfive.authservice.entity.dto.QInstructorDTO;
import com.highfive.authservice.repository.InstructorRepository;
import com.highfive.authservice.utils.exception.DepartmentNotFoundException;
import com.highfive.authservice.utils.exception.UserNotFoundException;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class InstructorService {

	@Autowired
	private InstructorRepository repository;
	private QLecturer lecturer = QLecturer.lecturer;

	@Inject
	@Lazy
	private UserService userService;
	private QUser user = QUser.user;

	@Inject
	@Lazy
	private DepartmentService departmentService;
	private QDepartment department = QDepartment.department;

	@Inject
	@Lazy
	private DepartmentManagerService departmentManagerService;

	@PersistenceContext
	EntityManager em;

	public Lecturer addInstructor(User user, Integer departmentId) {
		Lecturer newLecturer = new Lecturer(user.getId(), departmentId, 100.0);
		return repository.save(newLecturer);
	}

	public List<Lecturer> getInstructors() {
		return repository.findAll();
	}

	public List<InstructorDTO> getInstructorDTOs() {

		JPAQuery<InstructorDTO> query = new JPAQuery<>(em);

		return query.select(new QInstructorDTO(user, department, lecturer.score))
				.from(lecturer).innerJoin(user).on(lecturer.userId.eq(user.id))
				.innerJoin(department).on(lecturer.departmentId.eq(department.id))
				.fetch();

	}

	public Lecturer getInstructorById(String userId) throws UserNotFoundException {
		JPAQuery<Lecturer> query = new JPAQuery<>(em);
		Lecturer lecturerResponse = query.select(lecturer).from(lecturer)
				.innerJoin(user).on(lecturer.userId.eq(user.id).and(user.id.eq(userId)))
				.fetchFirst();
		if (lecturerResponse == null)
			throw new UserNotFoundException();
		return lecturerResponse;
	}

	public InstructorDTO getInstructorDTOById(String userId)
			throws UserNotFoundException, DepartmentNotFoundException {
		User user = userService.getUserById(userId);
		if (user == null)
			throw new UserNotFoundException();
		Lecturer lecturer = getInstructorById(userId);
		if (lecturer == null)
			throw new UserNotFoundException();
		Department department = departmentService
				.getDepartmentById(lecturer.getDepartmentId());
		if (department == null)
			throw new DepartmentNotFoundException();

		return new InstructorDTO(user, department, lecturer.getScore());
	}

	@Transactional
	public Lecturer setInstructor(InstructorDTO instructorDTO)
			throws UserNotFoundException, DepartmentNotFoundException {
		Lecturer newLecturer = getInstructorById(instructorDTO.getUserDTO().getId());

		userService.setUser(instructorDTO.getUserDTO());

		if (instructorDTO.getDepartment() != null)
			newLecturer.setDepartmentId(instructorDTO.getDepartment().getId());
		if (instructorDTO.getScore() != null)
			newLecturer.setScore(instructorDTO.getScore());

		return repository.save(newLecturer);
	}

	@Transactional
	public void removeInstructorById(String id) throws DepartmentNotFoundException {
		departmentManagerService.removeDepartmentManager(id);
		repository.deleteById(id);
	}

}
