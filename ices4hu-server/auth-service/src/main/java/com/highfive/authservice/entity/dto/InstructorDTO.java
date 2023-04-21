package com.highfive.authservice.entity.dto;

import com.highfive.authservice.entity.User;
import com.querydsl.core.annotations.QueryProjection;

public class InstructorDTO {

	private Integer instructorId;
	private User user;
	private Integer departmentId;
	private Double score;
	private Boolean isDepartmentManager;

	@QueryProjection
	public InstructorDTO(Integer instructorId, User user, Integer departmentId,
			Double score, Boolean isDepartmentManager) {
		super();
		this.instructorId = instructorId;
		this.user = user;
		this.departmentId = departmentId;
		this.score = score;
		this.isDepartmentManager = isDepartmentManager;
	}

	public Integer getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
