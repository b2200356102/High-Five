package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Department;
import com.highfive.authservice.entity.Instructor;
import com.highfive.authservice.entity.QDepartment;
import com.highfive.authservice.entity.QInstructor;
import com.highfive.authservice.entity.QStudent;
import com.highfive.authservice.entity.Student;
import com.highfive.authservice.entity.dto.DepartmentDTO;
import com.highfive.authservice.entity.dto.QDepartmentDTO;
import com.highfive.authservice.repository.DepartmentRepository;
import com.highfive.authservice.utils.exception.DepartmentNotFoundException;
import com.highfive.authservice.utils.exception.InstructorNotFoundException;
import com.highfive.authservice.utils.exception.StudentNotFoundException;
import com.highfive.authservice.utils.exception.UserNotFoundException;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;
	private QDepartment department = QDepartment.department;

	@Autowired
	private InstructorService instructorService;
	private QInstructor instructor = QInstructor.instructor;

	@Autowired
	private StudentService studentService;
	private QStudent student = QStudent.student;

	@PersistenceContext
	private EntityManager em;

	public Department addDepartment(DepartmentDTO departmentDTO) {
		Department department = new Department(departmentDTO.getDepartmentId(),
				departmentDTO.getDepartmentManager().getUser().getId(),
				departmentDTO.getName());
		return repository.save(department);
	}

	public List<Department> getDepartments() {
		return repository.findAll();
	}

	public List<DepartmentDTO> getDepartmentDTOs() {

		JPAQuery<DepartmentDTO> query = new JPAQuery<>(em);
		return query.select(new QDepartmentDTO(department.id, department.name, null))
				.from(department).fetch();
	}

	public Department getDepartmentById(Integer id) throws DepartmentNotFoundException {
		Department department = repository.findById(id).orElse(null);
		if (department == null)
			throw new DepartmentNotFoundException();
		return department;
	}

	public DepartmentDTO getDepartmentDTOById(Integer id)
			throws DepartmentNotFoundException, InstructorNotFoundException,
			UserNotFoundException {
		Department department = repository.findById(id).orElse(null);
		if (department == null)
			throw new DepartmentNotFoundException();
		DepartmentDTO departmentDTO = new DepartmentDTO(id, department.getName(),
				instructorService
						.getInstructorDTOByUserId(department.getDepartmentManagerId()));
		return departmentDTO;
	}

	public Department setDepartment(DepartmentDTO departmentDTO)
			throws DepartmentNotFoundException {
		Department newDepartment = repository.findById(departmentDTO.getDepartmentId())
				.orElse(null);
		if (newDepartment == null)
			throw new DepartmentNotFoundException();

		if (departmentDTO.getName() != null)
			newDepartment.setName(departmentDTO.getName());
		if (departmentDTO.getDepartmentManager() != null)
			newDepartment.setDepartmentManagerId(
					departmentDTO.getDepartmentManager().getUser().getId());

		return repository.save(newDepartment);
	}

	@Transactional
	public void removeDepartment(Integer id)
			throws StudentNotFoundException, InstructorNotFoundException {
		JPAQuery<Student> studentQuery = new JPAQuery<>(em);
		List<Student> students = studentQuery.select(student).from(student)
				.innerJoin(department).on(student.departmentId.eq(department.id))
				.where(department.id.eq(id)).fetch();
		JPAQuery<Instructor> instructorQuery = new JPAQuery<>(em);
		List<Instructor> instructors = instructorQuery.select(instructor).from(instructor)
				.innerJoin(department).on(instructor.departmentId.eq(department.id))
				.where(department.id.eq(id)).fetch();

		for (Student s : students)
			studentService.removeStudentById(s.getId());
		for (Instructor i : instructors)
			instructorService.removeInstructorById(i.getId());
		repository.deleteById(id);
	}

}
