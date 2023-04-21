package com.highfive.authservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "INSTRUCTOR", schema = "AUTH")
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Integer id;

	@Column(name = "USER_ID", nullable = false, length = 10)
	private String userId;

	@Column(name = "DEPARTMENT_ID")
	private Integer departmentId;

	@Column(name = "SCORE", nullable = false)
	private Short score;

	@Column(name = "IS_DEPARTMENT_MANAGER", nullable = false)
	private Boolean isDepartmentManager;

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

	public Short getScore() {
		return score;
	}

	public void setScore(Short score) {
		this.score = score;
	}

	public Boolean getIsDepartmentManager() {
		return isDepartmentManager;
	}

	public void setIsDepartmentManager(Boolean isDepartmentManager) {
		this.isDepartmentManager = isDepartmentManager;
	}

}
