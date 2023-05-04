package com.highfive.enrollmentservice.service;

import com.highfive.enrollmentservice.DTO.CourseTakenDTO;

public interface CourseTakenService {

	CourseTakenDTO getCourseTakenById(Long courseTakenId);

	CourseTakenDTO createCourseTaken(CourseTakenDTO courseTakenDTO);

	CourseTakenDTO deleteCourseTaken(Long courseTakenId);

	CourseTakenDTO updateCourseTaken(CourseTakenDTO courseTakenDTO, Long courseTakenId);
}
