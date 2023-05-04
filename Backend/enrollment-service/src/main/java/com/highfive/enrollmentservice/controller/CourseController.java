package com.highfive.enrollmentservice.controller;

import com.highfive.enrollmentservice.DTO.CourseDTO;
import com.highfive.enrollmentservice.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CourseController {

	private CourseService courseService;

	@GetMapping("{courseId}")
	public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long courseId) {
		return new ResponseEntity<>(courseService.getCourseById(courseId), HttpStatus.FOUND);
	}

	@GetMapping
	public ResponseEntity<List<CourseDTO>> getAllCourses() {
		return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.FOUND);
	}

	@GetMapping("/department/{departmentID}")
	public ResponseEntity<List<CourseDTO>> getAllCoursesWithParticularDepartment(@PathVariable Long departmentID) {
		return new ResponseEntity<>(courseService.getAllCoursesWithParticularDepartment(departmentID), HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
		return new ResponseEntity<>(courseService.createCourse(courseDTO), HttpStatus.CREATED);
	}

	@DeleteMapping("{courseId}")
	public ResponseEntity<CourseDTO> deleteCourse(@PathVariable Long courseId) {

		return new ResponseEntity<>(courseService.deleteCourse(courseId), HttpStatus.OK);
	}

	@PutMapping("{courseId}")
	public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long courseId, @Valid @RequestBody CourseDTO courseDTO) {
		return new ResponseEntity<>(courseService.updateCourse(courseDTO, courseId), HttpStatus.OK);
	}


}
