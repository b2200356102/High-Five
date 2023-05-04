package com.highfive.enrollmentservice.service.impl;

import com.highfive.enrollmentservice.entity.Course;
import com.highfive.enrollmentservice.entity.CourseTaken;
import com.highfive.enrollmentservice.exception.ResourceNotFoundException;
import com.highfive.enrollmentservice.DTO.CourseDTO;
import com.highfive.enrollmentservice.DTO.CourseTakenDTO;
import com.highfive.enrollmentservice.repository.CourseTakenRepository;
import com.highfive.enrollmentservice.service.CourseTakenService;
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
public class CourseTakenServiceImpl implements CourseTakenService {

	private final String COURSE_TAKEN_NOT_FOUND_ERROR_CODE = "COURSE_TAKEN_NOT_FOUND";

	private ModelMapper modelMapper;
	private CourseTakenRepository courseTakenRepository;
	private RestTemplate restTemplate;

	@Override
	public CourseTakenDTO getCourseTakenById(Long courseTakenId) {
		CourseTaken theCourseTaken = courseTakenRepository.findCourseTakenById(courseTakenId).orElseThrow(() -> new ResourceNotFoundException("Course Given", "id", String.valueOf(courseTakenId), COURSE_TAKEN_NOT_FOUND_ERROR_CODE));

		return modelMapper.map(theCourseTaken, CourseTakenDTO.class);
	}

	@Override
	public CourseTakenDTO createCourseTaken(CourseTakenDTO courseTakenDTO) {

		String url = "http://localhost:8081/api/courses/" + courseTakenDTO.getCourseId();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<CourseDTO> response;
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, entity, CourseDTO.class);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Course", "id", String.valueOf(courseTakenDTO.getCourseId()), "COURSE_NOT_FOUND");
		}


		CourseTaken courseTaken = modelMapper.map(courseTakenDTO, CourseTaken.class);
		courseTaken.setCourse(modelMapper.map(response.getBody(), Course.class));
		Long id = courseTakenRepository.save(courseTaken).getId();
		courseTakenRepository.findCourseTakenById(id).orElseThrow(() -> new ResourceNotFoundException("Course Given", "id", String.valueOf(id), COURSE_TAKEN_NOT_FOUND_ERROR_CODE));

		CourseTakenDTO createdModel = modelMapper.map(courseTaken, CourseTakenDTO.class);
		createdModel.setCourseId(courseTaken.getId());

		return createdModel;
	}

	@Override
	public CourseTakenDTO deleteCourseTaken(Long courseTakenId) {
		CourseTaken willBeDeletedCourseTaken = courseTakenRepository.findCourseTakenById(courseTakenId).orElseThrow(() -> new ResourceNotFoundException("Course Given", "id", String.valueOf(courseTakenId), COURSE_TAKEN_NOT_FOUND_ERROR_CODE));
		courseTakenRepository.delete(willBeDeletedCourseTaken);
		return modelMapper.map(willBeDeletedCourseTaken, CourseTakenDTO.class);
	}

	@Override
	public CourseTakenDTO updateCourseTaken(CourseTakenDTO courseTakenDTO, Long courseTakenId) {
		CourseTaken willBeSavedCourseTaken = courseTakenRepository.findCourseTakenById(courseTakenId).orElseThrow(() -> new ResourceNotFoundException("Course Given", "id", String.valueOf(courseTakenId), COURSE_TAKEN_NOT_FOUND_ERROR_CODE));
		courseTakenRepository.save(willBeSavedCourseTaken);
		CourseTaken updatedCourseTaken = courseTakenRepository.findCourseTakenById(courseTakenId).orElseThrow(() -> new ResourceNotFoundException("Course Given", "id", String.valueOf(courseTakenId), "COURSE_TAKEN_CANNOT_BE_UPDATED"));

		return modelMapper.map(updatedCourseTaken, CourseTakenDTO.class);
	}
}
