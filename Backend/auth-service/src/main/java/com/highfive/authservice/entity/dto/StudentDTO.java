package com.highfive.authservice.entity.dto;

import com.highfive.authservice.entity.Department;
import com.highfive.authservice.entity.User;
import com.querydsl.core.annotations.QueryProjection;

public class StudentDTO {

	private UserDTO userDTO;
	private Department department;
	private Short semester;
	private Boolean undergrad;
	private Boolean banned;

	@QueryProjection
	public StudentDTO() {
		super();
	}

	@QueryProjection
	public StudentDTO(User user, Department department, Short semester, Boolean undergrad,
			Boolean banned) {
		super();
		this.userDTO = user.toUserDTO();
		this.department = department;
		this.semester = semester;
		this.undergrad = undergrad;
		this.banned = banned;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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
