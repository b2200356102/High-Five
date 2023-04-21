package com.highfive.authservice.entity.dto;

import com.querydsl.core.annotations.QueryProjection;

public class AdminDTO {

	private Integer adminId;
	private UserDTO user;

	@QueryProjection
	public AdminDTO(Integer adminId, UserDTO user) {
		super();
		this.adminId = adminId;
		this.user = user;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}
