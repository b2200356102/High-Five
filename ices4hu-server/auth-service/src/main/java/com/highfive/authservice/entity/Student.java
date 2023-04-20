package com.highfive.authservice.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "STUDENT", schema = "AUTH")
public class Student implements Serializable {

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	private Integer id;

	@Column(name = "USER_ID", nullable = false, unique = true)
	private Integer student_id;

	@Column(name = "DEPARTMENT_ID")
	private Integer departmentId;

	@Column(name = "SEMESTER")
	private Short semester;

	@Column(name = "STUDENT_TYPE")
	private Boolean studentType;

	@Column(name = "IS_BANNED", nullable = false)
	private Boolean isBanned;

}
