package com.highfive.authservice.entity.dto;

import com.querydsl.core.annotations.QueryProjection;

public class UserDTO {

	private Integer id;
	private String name;
	private String surname;
	private String mail;
	private Short role;

	@QueryProjection
	public UserDTO(Integer id, String name, String surname, String mail, Short role) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Short getRole() {
		return role;
	}

	public void setRole(Short role) {
		this.role = role;
	}

}
