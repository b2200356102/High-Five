package com.highfive.authservice.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "STUDENT", schema = "AUTH")
public class Student implements Serializable {

	@Id
	@Column(name = "USER_ID", nullable = false, length = 10)
	@Length(min = 10, max = 10, message = "USER ID MUST BE 10 CHARACTERS")
	private String userId;

	@Column(name = "DEPARTMENT_ID")
	private Integer departmentId;

	@Column(name = "SEMESTER")
	private Short semester;

	@Column(name = "UNDERGRAD")
	private Boolean undergrad;

	@Column(name = "BANNED", nullable = false)
	private Boolean banned;

	public Student() {
		super();
	}

	public Student(String userId, Integer departmentId, Short semester, Boolean undergrad,
			Boolean banned) {
		super();
		this.userId = userId;
		this.departmentId = departmentId;
		this.semester = semester;
		this.undergrad = undergrad;
		this.banned = banned;
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

	public Short getSemester() {
		return semester;
	}

	public void setSemester(Short semester) {
		this.semester = semester;
	}

	public Boolean getUndergrad() {
		return undergrad;
	}

	public void setUndergrad(Boolean undergrad) {
		this.undergrad = undergrad;
	}

	public Boolean getBanned() {
		return banned;
	}

	public void setBanned(Boolean banned) {
		this.banned = banned;
	}

}
