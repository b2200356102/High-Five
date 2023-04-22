package com.highfive.authservice.entity.dto;

import com.highfive.authservice.entity.Department;
import com.highfive.authservice.entity.User;
import com.querydsl.core.annotations.QueryProjection;

public class StudentDTO {

	private Integer studentId;
	private User user;
	private Department department;
	private Short semester;
	private Boolean isUndergrad;
	private Boolean isBanned;

	@QueryProjection
	public StudentDTO(Integer studentId, User user, Department department, Short semester,
			Boolean isUndergrad, Boolean isBanned) {
		super();
		this.studentId = studentId;
		this.user = user;
		this.department = department;
		this.semester = semester;
		this.isUndergrad = isUndergrad;
		this.isBanned = isBanned;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Short getSemester() {
		return semester;
	}

	public void setSemester(Short semester) {
		this.semester = semester;
	}

	public Boolean getIsUndergrad() {
		return isUndergrad;
	}

	public void setIsUndergrad(Boolean isUndergrad) {
		this.isUndergrad = isUndergrad;
	}

	public Boolean getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(Boolean isBanned) {
		this.isBanned = isBanned;
	}

}
