package com.highfive.authservice.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "DEPARTMENT", schema = "AUTH")
public class Department implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Integer id;

	@Column(name = "DEPARTMENT_MANAGER_ID")
	private String departmentManagerId;

	@Column(name = "NAME", nullable = false, length = 20)
	@NotNull(message = "DEPARTMENT NAME CANNOT BE NULL")
	@Length(max = 20, message = "DEPARTMENT NAME MUST BE LESS THAN 20 CHARACTERS")
	private String name;

	public Department() {
		super();
	}

	public Department(Integer id, String departmentManagerId, String name) {
		super();
		this.id = id;
		this.departmentManagerId = departmentManagerId;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentManagerId() {
		return departmentManagerId;
	}

	public void setDepartmentManagerId(String departmentManagerId) {
		this.departmentManagerId = departmentManagerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
