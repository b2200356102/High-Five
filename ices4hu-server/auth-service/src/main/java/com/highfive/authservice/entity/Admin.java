package com.highfive.authservice.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ADMIN", schema = "AUTH")
public class Admin implements Serializable {

	@Id
	@Column(name = "USER_ID", nullable = false, length = 10)
	private String userId;

	public Admin() {
		super();
	}

	public Admin(String userId) {
		super();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
