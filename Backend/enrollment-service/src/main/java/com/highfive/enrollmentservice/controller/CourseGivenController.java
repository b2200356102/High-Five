package com.highfive.enrollmentservice.controller;

import com.highfive.enrollmentservice.DTO.CourseGivenDTO;
import com.highfive.enrollmentservice.service.CourseGivenService;
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

@RestController
@AllArgsConstructor
@RequestMapping("/api/course-given")
public class CourseGivenController {

	private CourseGivenService courseGivenService;

	@GetMapping("{courseGivenId}")
	public ResponseEntity<CourseGivenDTO> getCourseGivenById(@PathVariable Long courseGivenId) {
		return new ResponseEntity<>(courseGivenService.getCourseGivenById(courseGivenId), HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<CourseGivenDTO> createCourseGiven(@RequestBody CourseGivenDTO courseGivenDTO) {
		return new ResponseEntity<>(courseGivenService.createCourseGiven(courseGivenDTO), HttpStatus.CREATED);
	}

	@DeleteMapping("{courseGivenId}")
	public ResponseEntity<CourseGivenDTO> deleteCourseGiven(@PathVariable Long courseGivenId) {

		return new ResponseEntity<>(courseGivenService.deleteCourseGiven(courseGivenId), HttpStatus.OK);
	}

	@PutMapping("{courseGivenId}")
	public ResponseEntity<CourseGivenDTO> updateCourseGiven(@PathVariable Long courseGivenId, @RequestBody CourseGivenDTO courseGivenDTO) {
		return new ResponseEntity<>(courseGivenService.updateCourseGiven(courseGivenDTO, courseGivenId), HttpStatus.OK);
	}
}
