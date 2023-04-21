package com.highfive.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Department;
import com.highfive.authservice.entity.dto.DepartmentDTO;
import com.highfive.authservice.repository.DepartmentRepository;
import com.highfive.authservice.utils.exception.DepartmentNotFoundException;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;

	public Department addDepartment(DepartmentDTO departmentDTO) {
		Department department = new Department(departmentDTO.getDepartmentId(),
				departmentDTO.getDepartmentManager().getUser().getId(),
				departmentDTO.getName());
		return repository.save(department);
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

//	@Autowired
//	UserService userService;
//	QUser user = QUser.user;
//
//	QDepartment department = QDepartment.department;
//
//	@PersistenceContext
//	EntityManager em;
//
//	public Department addDepartment(Department department) {
//		return repository.save(department);
//	}
//
//	public List<Department> getDepartments() {
//		return repository.findAll();
//	}
//
//	public Department getDepartmentById(Integer departmentId) {
//		return repository.findById(departmentId).orElse(null);
//	}
//
//	public UserDTO getDepartmentManager(Integer departmentId) {
//		Department department = repository.findById(departmentId).orElse(null);
//		return userService.getUserById(department.getDepartmentManagerId());
//	}
//
//
//	public void deleteDepartment(Integer departmentId) {
//		repository.deleteById(departmentId);
//	}

}
