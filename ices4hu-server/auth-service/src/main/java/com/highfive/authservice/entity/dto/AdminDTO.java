package com.highfive.authservice.entity.dto;

import com.highfive.authservice.entity.User;
import com.querydsl.core.annotations.QueryProjection;

public class AdminDTO {

	private Integer adminId;
	private User user;

	@QueryProjection
	public AdminDTO(Integer adminId, User user) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
