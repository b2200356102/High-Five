package com.highfive.enrollmentservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseTakenDTO {
	private Long id;

	private Long studentId;

	private Long courseId;
}
