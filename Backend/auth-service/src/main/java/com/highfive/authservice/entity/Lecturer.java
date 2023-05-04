package com.highfive.authservice.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "INSTRUCTOR", schema = "AUTH")
public class Lecturer implements Serializable {

	@Id
	@Column(name = "USER_ID", nullable = false, length = 10)
	@Length(min = 10, max = 10, message = "USER ID MUST BE 10 CHARACTERS")
	private String userId;

	@Column(name = "DEPARTMENT_ID")
	private Integer departmentId;

	@Column(name = "SCORE", nullable = false)
	private Double score;

	public Lecturer() {
		super();
	}

	public Lecturer(String userId, Integer departmentId, Double score) {
		super();
		this.userId = userId;
		this.departmentId = departmentId;
		this.score = score;
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

}
