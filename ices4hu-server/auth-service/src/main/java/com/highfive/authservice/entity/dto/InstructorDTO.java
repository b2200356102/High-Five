package com.highfive.authservice.entity.dto;

import com.highfive.authservice.entity.Department;
import com.querydsl.core.annotations.QueryProjection;

public class InstructorDTO {

	private UserDTO userDTO;
	private Department department;
	private Double score;

	@QueryProjection
	public InstructorDTO(UserDTO userDTO, Department department, Double score) {
		super();
		this.userDTO = userDTO;
		this.department = department;
		this.score = score;
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

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

}
