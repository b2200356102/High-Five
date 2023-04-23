package com.highfive.authservice.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "USER", schema = "AUTH")
public class User implements Serializable {

	@Id
	@Column(name = "ID", nullable = false, unique = true, length = 10)
	@Length(min = 10, max = 10, message = "USER ID MUST BE 10 CHARACTERS")
	private String id;

	@Column(name = "NAME", nullable = false, length = 25)
	@NotNull(message = "USER NAME CANNOT BE NULL")
	@Length(max = 25, message = "USER NAME MUST BE LESS THAN 25 CHARACTERS")
	private String name;

	@Column(name = "SURNAME", nullable = false, length = 25)
	@NotNull(message = "USER SURNAME CANNOT BE NULL")
	@Length(max = 25, message = "USER SURNAME MUST BE LESS THAN 25 CHARACTERS")
	private String surname;

	@Column(name = "MAIL", nullable = false, length = 20)
	@Email(message = "INVALID MAIL ADDRESS")
	private String mail;

	@Column(name = "PASSWORD", nullable = false, length = 20)
	@Length(min = 10, max = 20, message = "USER ID MUST BE BETWEEN 10-20 CHARACTERS")
	private String password;

	@Column(name = "ROLE", nullable = false)
	private Short role;

	@Column(name = "IS_PENDING", nullable = false)
	private Boolean isPending;

	public User() {
		super();
	}

	public User(String id, String name, String surname, String mail, String password,
			Short role, Boolean isPending) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.password = password;
		this.role = role;
		this.isPending = isPending;
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

	public Boolean getIsPending() {
		return isPending;
	}

	public void setIsPending(Boolean isPending) {
		this.isPending = isPending;
	}

}
