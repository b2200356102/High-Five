package com.highfive.authservice.entity.dto;

import com.querydsl.core.annotations.QueryProjection;

public class UserDTO {

	private String id;
	private String name;
	private String surname;
	private String mail;
	private Boolean pending;

	@QueryProjection
	public UserDTO() {
		super();
	}

	@QueryProjection
	public UserDTO(String id, String name, String surname, String mail, Boolean pending) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.pending = pending;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Boolean getPending() {
		return pending;
	}

	public void setPending(Boolean pending) {
		this.pending = pending;
	}

}
