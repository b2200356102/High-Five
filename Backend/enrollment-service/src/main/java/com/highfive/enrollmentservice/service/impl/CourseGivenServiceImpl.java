package com.highfive.enrollmentservice.service.impl;

import com.highfive.enrollmentservice.entity.Course;
import com.highfive.enrollmentservice.entity.CourseGiven;
import com.highfive.enrollmentservice.exception.ResourceNotFoundException;
import com.highfive.enrollmentservice.DTO.CourseGivenDTO;
import com.highfive.enrollmentservice.DTO.CourseDTO;
import com.highfive.enrollmentservice.repository.CourseGivenRepository;
import com.highfive.enrollmentservice.service.CourseGivenService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseGivenServiceImpl implements CourseGivenService {
	private final String COURSE_GIVEN_NOT_FOUND_ERROR_CODE = "COURSE_GIVEN_NOT_FOUND";
	private ModelMapper modelMapper;
	private CourseGivenRepository courseGivenRepository;
	private RestTemplate restTemplate;
	@Override
	public CourseGivenDTO getCourseGivenById(Long courseGivenId) {
		CourseGiven theCourseGiven = courseGivenRepository.findCourseGivenById(courseGivenId).orElseThrow(() -> new ResourceNotFoundException("Course Given", "id", String.valueOf(courseGivenId), COURSE_GIVEN_NOT_FOUND_ERROR_CODE));

		return modelMapper.map(theCourseGiven, CourseGivenDTO.class);

	}

	@Override
	public CourseGivenDTO createCourseGiven(CourseGivenDTO courseGivenDTO) {
		// check course is exist
		String url = "http://localhost:8081/api/courses/" + courseGivenDTO.getCourseId();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<CourseDTO> response;
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, entity, CourseDTO.class);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Course", "id", String.valueOf(courseGivenDTO.getCourseId()), "COURSE_NOT_FOUND");
		}


		// Later check for instructor
		CourseGiven courseGiven = modelMapper.map(courseGivenDTO, CourseGiven.class);
		courseGiven.setCourse(modelMapper.map(response.getBody(), Course.class));
		Long id = courseGivenRepository.save(courseGiven).getId();
		courseGivenRepository.findCourseGivenById(id).orElseThrow(() -> new ResourceNotFoundException("Course Given", "id", String.valueOf(id), COURSE_GIVEN_NOT_FOUND_ERROR_CODE));

		CourseGivenDTO createdModel = modelMapper.map(courseGiven, CourseGivenDTO.class);
		createdModel.setCourseId(courseGiven.getId());
		return createdModel;
	}

	@Override
	public CourseGivenDTO deleteCourseGiven(Long courseGivenId) {
		CourseGiven willBeDeletedCourse = courseGivenRepository.findCourseGivenById(courseGivenId).orElseThrow(() -> new ResourceNotFoundException("Course Given", "id", String.valueOf(courseGivenId), COURSE_GIVEN_NOT_FOUND_ERROR_CODE));
		courseGivenRepository.delete(willBeDeletedCourse);
		return modelMapper.map(willBeDeletedCourse, CourseGivenDTO.class);
	}

	@Override
	public CourseGivenDTO updateCourseGiven(CourseGivenDTO courseGivenDTO, Long courseGivenId) {
		CourseGiven willBeSavedCourseGiven = courseGivenRepository.findCourseGivenById(courseGivenId).orElseThrow(() -> new ResourceNotFoundException("Course Given", "id", String.valueOf(courseGivenId), COURSE_GIVEN_NOT_FOUND_ERROR_CODE));
		courseGivenRepository.save(willBeSavedCourseGiven);
		CourseGiven updatedCourseGiven = courseGivenRepository.findCourseGivenById(courseGivenId).orElseThrow(() -> new ResourceNotFoundException("Course Given", "id", String.valueOf(courseGivenId), "COURSE_GIVEN_CANNOT_BE_UPDATED"));

		return modelMapper.map(updatedCourseGiven, CourseGivenDTO.class);
	}


}
