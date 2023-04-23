package com.highfive.authservice.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "INSTRUCTOR", schema = "AUTH")
public class Instructor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Integer id;

	@Column(name = "USER_ID", nullable = false, length = 10)
	@Length(min = 10, max = 10, message = "USER ID MUST BE 10 CHARACTERS")
	private String userId;

	@Column(name = "DEPARTMENT_ID")
	private Integer departmentId;

	@Column(name = "SCORE", nullable = false)
	private Double score;

	@Column(name = "IS_DEPARTMENT_MANAGER", nullable = false)
	private Boolean isDepartmentManager;

	public Instructor() {
		super();
	}

	public Instructor(Integer id, String userId, Integer departmentId, Double score,
			Boolean isDepartmentManager) {
		super();
		this.id = id;
		this.userId = userId;
		this.departmentId = departmentId;
		this.score = score;
		this.isDepartmentManager = isDepartmentManager;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Boolean getIsDepartmentManager() {
		return isDepartmentManager;
	}

	public void setIsDepartmentManager(Boolean isDepartmentManager) {
		this.isDepartmentManager = isDepartmentManager;
	}

}
