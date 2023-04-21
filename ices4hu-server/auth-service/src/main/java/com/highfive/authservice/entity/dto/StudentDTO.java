package com.highfive.authservice.entity.dto;

import com.querydsl.core.annotations.QueryProjection;

public class StudentDTO {

	private Integer studentId;
	private UserDTO user;
	private Integer departmentId;
	private DepartmentDTO department;
	private Short semester;
	private Boolean isUndergrad;
	private Boolean isBanned;

	@QueryProjection
	public StudentDTO(Integer studentId, UserDTO user, Integer departmentId,
			DepartmentDTO department, Short semester, Boolean isUndergrad, Boolean isBanned) {
		super();
		this.studentId = studentId;
		this.user = user;
		this.departmentId = departmentId;
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public DepartmentDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDTO department) {
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
