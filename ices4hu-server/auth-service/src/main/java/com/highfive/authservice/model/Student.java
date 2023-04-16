package com.highfive.authservice.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "STUDENT", schema = "AUTH")
public class Student implements Serializable {

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID")
	private User user;

	@Column(name = "DEPARTMENT_ID", nullable = false)
	private Long departmentId;

	@Column(name = "SEMESTER", nullable = false)
	private Short semester;

	@Column(name = "STUDENT_TYPE", nullable = false)
	private Boolean studentType;

	@Column(name = "IS_BANNED", nullable = false)
	private Boolean isBanned;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Short getSemester() {
		return semester;
	}

	public void setSemester(Short semester) {
		this.semester = semester;
	}

	public Boolean getStudentType() {
		return studentType;
	}

	public void setStudentType(Boolean studentType) {
		this.studentType = studentType;
	}

	public Boolean getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(Boolean isBanned) {
		this.isBanned = isBanned;
	}

}
