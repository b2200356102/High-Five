package com.highfive.enrollmentservice.service.impl;

import com.highfive.enrollmentservice.entity.Course;
import com.highfive.enrollmentservice.exception.ResourceNotFoundException;
import com.highfive.enrollmentservice.DTO.CourseDTO;
import com.highfive.enrollmentservice.repository.CourseRepository;
import com.highfive.enrollmentservice.service.CourseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

	private final String COURSE_NOT_FOUND_ERROR_CODE = "COURSE_NOT_FOUND";
	private CourseRepository courseRepository;
	private ModelMapper modelMapper;


	@Override
	public CourseDTO getCourseById(Long courseId) {
		Course theCourse = courseRepository.findCourseById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(courseId),COURSE_NOT_FOUND_ERROR_CODE));

		return modelMapper.map(theCourse, CourseDTO.class);
	}

	@Override
	public CourseDTO createCourse(CourseDTO courseDTO) {
		Course course = modelMapper.map(courseDTO, Course.class);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8082/api/departments/?departmentName="+courseDTO.getDepartmentName();
		ResponseEntity<Map<String, Object>> response;
		try {
			response = restTemplate.exchange(
					url,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<>() {
					}
			);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Department", "department name", courseDTO.getDepartmentName(), "DEPARTMENT_NOT_FOUND");
		}

		Map<String, Object> departmentMap = response.getBody();
		Long departmentId = ((Integer) departmentMap.get("id")).longValue();
		course.setDepartmentId(departmentId);

		Long theId = courseRepository.save(course).getId();
		Course savedCourse = courseRepository.findCourseById(theId).orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(theId),COURSE_NOT_FOUND_ERROR_CODE));
		CourseDTO savedDto = modelMapper.map(savedCourse, CourseDTO.class);
		savedDto.setDepartmentName(courseDTO.getDepartmentName());
		return savedDto;
	}

	@Override
	public CourseDTO deleteCourse(Long courseId) {
		Course willBeDeletedCourse = courseRepository.findCourseById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(courseId), COURSE_NOT_FOUND_ERROR_CODE));

		courseRepository.delete(willBeDeletedCourse);
		return modelMapper.map(willBeDeletedCourse, CourseDTO.class);
	}

	@Override
	public CourseDTO updateCourse(CourseDTO courseDTO, Long courseId) {
		Course willBeUpdatedCourse = courseRepository.findCourseById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "course code", String.valueOf(courseId), COURSE_NOT_FOUND_ERROR_CODE));
		willBeUpdatedCourse = modelMapper.map(courseDTO, Course.class);

		willBeUpdatedCourse.setId(courseId);
		courseRepository.save(willBeUpdatedCourse);
		Course updatedCourse = courseRepository.findCourseById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "course code", String.valueOf(courseId), "COURSE_CANNOT_BE_UPDATED"));

		return modelMapper.map(updatedCourse, CourseDTO.class);
	}

	@Override
	public List<CourseDTO> getAllCourses() {
		return Arrays.asList(modelMapper.map(courseRepository.findAll(), CourseDTO[].class));
	}

	@Override
	public List<CourseDTO> getAllCoursesWithParticularDepartment(Long departmentId) {
		return Arrays.asList(modelMapper.map(courseRepository.findAllByDepartmentId(departmentId), CourseDTO[].class));
	}
}
