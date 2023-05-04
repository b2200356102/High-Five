package com.highfive.enrollmentservice.controller;

import com.highfive.enrollmentservice.DTO.CourseTakenDTO;
import com.highfive.enrollmentservice.service.CourseTakenService;
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
@RequestMapping("/api/course-taken")
@AllArgsConstructor
public class CourseTakenController {
	private CourseTakenService courseTakenService;

	@GetMapping("{courseTakenId}")
	public ResponseEntity<CourseTakenDTO> getCourseGivenById(@PathVariable Long courseTakenId) {
		return new ResponseEntity<>(courseTakenService.getCourseTakenById(courseTakenId), HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<CourseTakenDTO> createCourseGiven(@RequestBody CourseTakenDTO courseTakenDTO) {
		return new ResponseEntity<>(courseTakenService.createCourseTaken(courseTakenDTO), HttpStatus.CREATED);
	}

	@DeleteMapping("{courseTakenId}")
	public ResponseEntity<CourseTakenDTO> deleteCourseGiven(@PathVariable Long courseTakenId) {

		return new ResponseEntity<>(courseTakenService.deleteCourseTaken(courseTakenId), HttpStatus.OK);
	}

	@PutMapping("{courseTakenId}")
	public ResponseEntity<CourseTakenDTO> updateCourseGiven(@PathVariable Long courseTakenId, @RequestBody CourseTakenDTO courseTakenDTO) {
		return new ResponseEntity<>(courseTakenService.updateCourseTaken(courseTakenDTO, courseTakenId), HttpStatus.OK);
	}
}
