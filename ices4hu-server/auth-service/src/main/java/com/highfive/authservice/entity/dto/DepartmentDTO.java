package com.highfive.authservice.entity.dto;

import com.querydsl.core.annotations.QueryProjection;

public class DepartmentDTO {

	private Integer departmentId;
	private String name;
	private InstructorDTO departmentManager;

	@QueryProjection
	public DepartmentDTO(Integer departmentId, String name, InstructorDTO departmentManager) {
		super();
		this.departmentId = departmentId;
		this.name = name;
		this.departmentManager = departmentManager;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InstructorDTO getDepartmentManager() {
		return departmentManager;
	}

	public void setDepartmentManager(InstructorDTO departmentManager) {
		this.departmentManager = departmentManager;
	}

}
