package com.highfive.authservice.entity.dto;

import com.querydsl.core.annotations.QueryProjection;

public class InstructorDTO {

	private Integer instructorId;
	private UserDTO user;
	private Integer departmentId;
	private String departmentName;
	private Double score;
	private Boolean isDepartmentManager;

	@QueryProjection
	public InstructorDTO(Integer instructorId, UserDTO user, Integer departmentId,
			String departmentName, Double score, Boolean isDepartmentManager) {
		super();
		this.instructorId = instructorId;
		this.user = user;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.score = score;
		this.isDepartmentManager = isDepartmentManager;
	}

	public Integer getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
