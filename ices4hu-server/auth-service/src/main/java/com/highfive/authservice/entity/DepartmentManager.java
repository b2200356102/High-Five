package com.highfive.authservice.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "DEPARTMENT_MANAGER", schema = "AUTH")
public class DepartmentManager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Integer id;

	@Column(name = "INSTRUCTOR_ID", nullable = false, length = 10)
	@Length(min = 10, max = 10, message = "INSTRUCTOR ID MUST BE 10 CHARACTERS")
	private String instructorId;

	@Column(name = "DEPARTMENT_ID", nullable = false, unique = true)
	@NotNull(message = "DEPARTMENT ID CANNOT BE EMPTY")
	private Integer departmentId;

	public DepartmentManager() {
		super();
	}

	public DepartmentManager(Integer id, String userId, Integer departmentId) {
		super();
		this.id = id;
		this.instructorId = userId;
		this.departmentId = departmentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return instructorId;
	}

	public void setUserId(String userId) {
		this.instructorId = userId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

}
