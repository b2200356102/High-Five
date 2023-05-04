package com.highfive.enrollmentservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
@Getter
@Setter
@Builder
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = false)
	private String courseCode;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer credit;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private Integer courseCapacity;

	@Column(nullable = false)
	private Integer numberOfStudents;

	@Column(nullable = false)
	private Boolean isUndergrad;

	@Column(nullable = false)
	private Long departmentId;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CourseGiven> courseGivenList;
}
