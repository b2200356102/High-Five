package com.highfive.authservice.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "STUDENT", schema = "AUTH")
public class Student implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Integer id;

	@Column(name = "USER_ID", nullable = false, length = 10)
	private String userId;

	@Column(name = "DEPARTMENT_ID")
	private Integer departmentId;

	@Column(name = "SEMESTER")
	private Short semester;

	@Column(name = "IS_UNDERGRAD")
	private Boolean isUndergrad;

	@Column(name = "IS_BANNED", nullable = false)
	private Boolean isBanned;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Short getSemester() {
		return semester;
	}

	public void setSemester(Short semester) {
		this.semester = semester;
	}

	public Boolean getIsUndergrad() {
		return isUndergrad;
	}

	public void setIsUndergrad(Boolean isUndergrad) {
		this.isUndergrad = isUndergrad;
	}

	public Boolean getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(Boolean isBanned) {
		this.isBanned = isBanned;
	}

}
