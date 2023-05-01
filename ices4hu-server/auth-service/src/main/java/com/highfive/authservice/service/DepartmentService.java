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
import com.highfive.authservice.entity.QStudent;
import com.highfive.authservice.entity.Student;
import com.highfive.authservice.repository.DepartmentRepository;
import com.highfive.authservice.utils.exception.DepartmentNotFoundException;
import com.highfive.authservice.utils.exception.InstructorNotFoundException;
import com.highfive.authservice.utils.exception.StudentNotFoundException;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;
	private QDepartment department = QDepartment.department;

	@Inject
	@Lazy
	private InstructorService instructorService;
	private QInstructor instructor = QInstructor.instructor;

	@Autowired
	private StudentService studentService;
	private QStudent student = QStudent.student;

	@PersistenceContext
	private EntityManager em;

	public Department addDepartment(Department department) {
		return repository.save(department);
	}

	public List<Department> getDepartments() {
		return repository.findAll();
	}

	public Department getDepartmentById(Integer id) throws DepartmentNotFoundException {
		Department department = repository.findById(id).orElse(null);
		if (department == null)
			throw new DepartmentNotFoundException();
		return department;
	}

	public Department setDepartment(Department department)
			throws DepartmentNotFoundException {
		Department newDepartment = repository.findById(department.getId()).orElse(null);
		if (newDepartment == null)
			throw new DepartmentNotFoundException();

		if (department.getName() != null)
			newDepartment.setName(department.getName());

		return repository.save(newDepartment);
	}

	@Transactional
	public void removeDepartment(Integer id) throws StudentNotFoundException,
			InstructorNotFoundException, DepartmentNotFoundException {

		JPAQuery<Student> studentQuery = new JPAQuery<>(em);
		List<Student> students = studentQuery.select(student).from(student)
				.innerJoin(department).on(student.departmentId.eq(department.id))
				.where(department.id.eq(id)).fetch();

		JPAQuery<Instructor> instructorQuery = new JPAQuery<>(em);
		List<Instructor> instructors = instructorQuery.select(instructor).from(instructor)
				.innerJoin(department).on(instructor.departmentId.eq(department.id))
				.where(department.id.eq(id)).fetch();

		for (Student s : students)
			studentService.removeStudentById(s.getUserId());
		for (Instructor i : instructors)
			instructorService.removeInstructorById(i.getUserId());
		repository.deleteById(id);
	}

}
