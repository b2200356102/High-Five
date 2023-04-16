package com.highfive.authservice.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "USER", schema = "AUTH")
public class User implements Serializable {

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 25)
	private String name;

	@Column(name = "SURNAME", nullable = false, length = 25)
	private String surname;

	@Column(name = "MAIL", nullable = false, length = 20, unique = true)
	private String mail;

	@Column(name = "PASSWORD", nullable = false, length = 20)
	private String password;

	@Column(name = "ROLE", nullable = false)
	private Short role;

	@Column(name = "status", nullable = false)
	private Boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Short getRole() {
		return role;
	}

	public void setRole(Short role) {
		this.role = role;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@OneToOne(cascade = CascadeType.ALL)
	private Student student;
}
