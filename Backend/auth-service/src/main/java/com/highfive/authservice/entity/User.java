package com.highfive.authservice.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.highfive.authservice.entity.dto.UserDTO;

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

	@Column(name = "NAME", nullable = false, length = 50)
	@NotNull(message = "USER NAME CANNOT BE NULL")
	@Length(max = 50, message = "USER NAME MUST BE LESS THAN 50 CHARACTERS")
	private String name;

	@Column(name = "SURNAME", nullable = false, length = 50)
	@NotNull(message = "USER NAME CANNOT BE NULL")
	@Length(max = 50, message = "USER NAME MUST BE LESS THAN 50 CHARACTERS")
	private String surname;

	@Column(name = "MAIL", nullable = false, length = 50)
	@Email(message = "INVALID MAIL ADDRESS")
	private String mail;

	@Column(name = "PASSWORD", nullable = false, length = 72)
	@Length(min = 6, max = 72, message = "PASSWORD MUST BE BETWEEN 6-72 CHARACTERS")
	private String password;

	@Column(name = "ROLE", nullable = false, length = 20)
	private String role;

	@Column(name = "PENDING", nullable = false)
	private Boolean pending;

	public User() {
		super();
	}

	public User(String id, String name, String surname, String mail, String password, String role,
			Boolean pending) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.password = password;
		this.role = role;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getPending() {
		return pending;
	}

	public void setPending(Boolean pending) {
		this.pending = pending;
	}

	public UserDTO toUserDTO() {
		return new UserDTO(id, surname, name, mail, pending);
	}
}
