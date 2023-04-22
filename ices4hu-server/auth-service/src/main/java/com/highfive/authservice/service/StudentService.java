package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.QDepartment;
import com.highfive.authservice.entity.QStudent;
import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.Student;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.QStudentDTO;
import com.highfive.authservice.entity.dto.StudentDTO;
import com.highfive.authservice.repository.StudentRepository;
import com.highfive.authservice.utils.exception.StudentNotFoundException;
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

	@Autowired
	private UserService userService;
	private QUser user = QUser.user;

	private QDepartment department = QDepartment.department;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Student addStudent(User user, Integer departmentId) {
		userService.addUser(user);
		Student newStudent = new Student(null, user.getId(), departmentId, (short) 1,
				true, false);
		return repository.save(newStudent);
	}

	public List<Student> getStudents() {
		return repository.findAll();
	}

	public List<StudentDTO> getStudentDTOs() {
		JPAQuery<StudentDTO> query = new JPAQuery<>(em);

		return query
				.select(new QStudentDTO(student.id, user, department, student.semester,
						student.isUndergrad, student.isBanned))
				.from(student).innerJoin(user).on(student.userId.eq(user.id))
				.innerJoin(department).on(student.departmentId.eq(department.id)).fetch();
	}

	public Student getStudentById(Integer id) throws StudentNotFoundException {
		Student student = repository.findById(id).orElse(null);
		if (student == null)
			throw new StudentNotFoundException();
		return student;
	}

	public StudentDTO getStudentDTOById(Integer id) throws StudentNotFoundException {
		JPAQuery<StudentDTO> query = new JPAQuery<>(em);
		StudentDTO studentDTO = query
				.select(new QStudentDTO(student.id, user, department, student.semester,
						student.isUndergrad, student.isBanned))
				.from(student).innerJoin(user).on(student.userId.eq(user.id))
				.innerJoin(department).on(student.departmentId.eq(department.id))
				.where(student.id.eq(id)).fetchFirst();
		if (studentDTO == null)
			throw new StudentNotFoundException();
		return studentDTO;
	}

	public Student getStudentByUserId(String userId) throws StudentNotFoundException {
		JPAQuery<Student> query = new JPAQuery<>(em);
		Student studentResponse = query.select(student).from(student).innerJoin(user)
				.on(student.userId.eq(user.id).and(user.id.eq(userId))).fetchFirst();
		if (studentResponse == null)
			throw new StudentNotFoundException();
		return studentResponse;
	}

	public StudentDTO getStudentDTOByUserId(String userId)
			throws StudentNotFoundException, UserNotFoundException {
		Student student = getStudentByUserId(userId);
		if (student == null)
			throw new StudentNotFoundException();
		return getStudentDTOById(student.getId());
	}

	@Transactional
	public Student setStudent(StudentDTO studentDTO) throws UserNotFoundException {
		Student newStudent = repository.findById(studentDTO.getStudentId()).orElse(null);
		if (newStudent == null)
			throw new UserNotFoundException();

		User user = userService.setUser(studentDTO.getUser());
		studentDTO.setUser(user);

		if (studentDTO.getSemester() != null)
			newStudent.setSemester(studentDTO.getSemester());
		if (studentDTO.getDepartment() != null)
			newStudent.setDepartmentId(studentDTO.getDepartment().getId());
		if (studentDTO.getIsUndergrad() != null)
			newStudent.setIsUndergrad(studentDTO.getIsUndergrad());
		if (studentDTO.getIsBanned() != null)
			newStudent.setIsBanned(studentDTO.getIsBanned());

		return repository.save(newStudent);
	}

	@Transactional
	public void removeStudentById(Integer id) throws StudentNotFoundException {
		repository.deleteById(id);
		userService.removeUserById(getStudentById(id).getUserId());
	}

	@Transactional
	public void removeStudentByUserId(String userId) throws StudentNotFoundException {
		repository.deleteById(getStudentByUserId(userId).getId());
		userService.removeUserById(userId);
	}

}
