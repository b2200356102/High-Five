package com.highfive.enrollmentservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseGivenDTO {

	private Long id;

	private Long instructorId;

	private Long courseId;
}
