package com.highfive.enrollmentservice.service;

import com.highfive.enrollmentservice.DTO.CourseDTO;

import java.util.List;

public interface CourseService {

	CourseDTO getCourseById(Long courseId);

	CourseDTO createCourse(CourseDTO courseDTO);

	CourseDTO deleteCourse(Long courseId);

	CourseDTO updateCourse(CourseDTO courseDTO, Long courseId);

	List<CourseDTO> getAllCourses();

	List<CourseDTO> getAllCoursesWithParticularDepartment(Long departmentId);
}
