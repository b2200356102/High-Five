package com.highfive.authservice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Department;
import com.highfive.authservice.entity.QDepartment;
import com.highfive.authservice.entity.QStudent;
import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.Student;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.QStudentDTO;
import com.highfive.authservice.entity.dto.QUserDTO;
import com.highfive.authservice.entity.dto.StudentDTO;
import com.highfive.authservice.entity.dto.UserDTO;
import com.highfive.authservice.repository.StudentRepository;
import com.highfive.authservice.utils.exception.DepartmentNotFoundException;
import com.highfive.authservice.utils.exception.UserNotFoundException;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	private QStudent student = QStudent.student;

	@Inject
	@Lazy
	private UserService userService;
	private QUser user = QUser.user;

	@Inject
	@Lazy
	private DepartmentService departmentService;
	private QDepartment department = QDepartment.department;

	@PersistenceContext
	private EntityManager em;

	public Student addStudent(User user, Integer departmentId) {
		Student newStudent = new Student(user.getId(), departmentId, (short) 1, true,
				false);
		return repository.save(newStudent);
	}

	public List<Student> getStudents() {
		return repository.findAll();
	}

	public List<StudentDTO> getStudentDTOs() {

		JPAQuery<UserDTO> subQuery = new JPAQuery<>(em);
		subQuery = subQuery
				.select(new QUserDTO(user.id, user.name, user.mail, user.pending))
				.from(user);

		JPAQuery<StudentDTO> query = new JPAQuery<>(em);

		return query
				.select(new QStudentDTO(subQuery, department, student.semester,
						student.undergrad, student.banned))
				.from(student).innerJoin(user).on(student.userId.eq(user.id))
				.innerJoin(department).on(student.departmentId.eq(department.id)).fetch();
	}

	public Student getStudentById(String userId) throws UserNotFoundException {
		JPAQuery<Student> query = new JPAQuery<>(em);
		Student studentResponse = query.select(student).from(student).innerJoin(user)
				.on(student.userId.eq(user.id).and(user.id.eq(userId))).fetchFirst();
		if (studentResponse == null)
			throw new UserNotFoundException();
		return studentResponse;
	}

	public StudentDTO getStudentDTOById(String userId)
			throws UserNotFoundException, DepartmentNotFoundException {

		User user = userService.getUserById(userId);
		Student student = getStudentById(userId);
		Department department = departmentService
				.getDepartmentById(student.getDepartmentId());

		return new StudentDTO(user.toUserDTO(), department, student.getSemester(),
				student.getUndergrad(), student.getBanned());
	}

	@Transactional
	public Student setStudent(StudentDTO studentDTO) throws UserNotFoundException {
		Student newStudent = repository.findById(studentDTO.getUserDTO().getId())
				.orElse(null);
		if (newStudent == null)
			throw new UserNotFoundException();

		userService.setUser(studentDTO.getUserDTO());

		if (studentDTO.getSemester() != null)
			newStudent.setSemester(studentDTO.getSemester());
		if (studentDTO.getDepartment() != null)
			newStudent.setDepartmentId(studentDTO.getDepartment().getId());
		if (studentDTO.getUndergrad() != null)
			newStudent.setUndergrad(studentDTO.getUndergrad());
		if (studentDTO.getBanned() != null)
			newStudent.setBanned(studentDTO.getBanned());

		return repository.save(newStudent);
	}

	public void removeStudentById(String userId) {
		repository.deleteById(userId);
	}

}
