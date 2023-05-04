package com.highfive.enrollmentservice.service;

import com.highfive.enrollmentservice.DTO.CourseGivenDTO;

public interface CourseGivenService {
	CourseGivenDTO getCourseGivenById(Long courseGivenId);

	CourseGivenDTO createCourseGiven(CourseGivenDTO courseGivenDTO);

	CourseGivenDTO deleteCourseGiven(Long courseGivenId);

	CourseGivenDTO updateCourseGiven(CourseGivenDTO courseGivenDTO, Long courseGivenId);
}
