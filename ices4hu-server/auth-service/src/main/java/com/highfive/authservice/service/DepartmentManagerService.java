package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.DepartmentManager;
import com.highfive.authservice.entity.QDepartment;
import com.highfive.authservice.entity.QDepartmentManager;
import com.highfive.authservice.repository.DepartmentManagerRepository;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class DepartmentManagerService {

	@Autowired
	DepartmentManagerRepository repository;
	QDepartmentManager departmentManager = QDepartmentManager.departmentManager;

	QDepartment department = QDepartment.department;

	@PersistenceContext
	private EntityManager em;

	public DepartmentManager addDepartmentManager(DepartmentManager departmentManager) {
		return repository.save(departmentManager);
	}

	public List<DepartmentManager> getDepartmentManagers() {
		return repository.findAll();
	}

	public DepartmentManager getDepartmentManagerByDepartmentId(Integer id) {
		JPAQuery<DepartmentManager> query = new JPAQuery<>(em);
		return query.select(departmentManager).from(departmentManager)
				.where(departmentManager.id.eq(id)).fetchFirst();
	}

	public DepartmentManager setDepartmentManager(Integer departmentId,
			String instructorId) {
		DepartmentManager departmentManager = getDepartmentManagerByDepartmentId(
				departmentId);
		departmentManager.setUserId(instructorId);
		return repository.save(departmentManager);
	}

	public void removeDepartmentManager(String instructorId) {
		JPAQuery<DepartmentManager> query = new JPAQuery<>(em);
		DepartmentManager departmentManagerResponse = query.select(departmentManager)
				.from(departmentManager)
				.where(departmentManager.instructorId.eq(instructorId)).fetchFirst();
		if (departmentManagerResponse == null)
			return;

		repository.deleteById(departmentManagerResponse.getId());
	}

}
