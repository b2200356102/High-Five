package com.highfive.enrollmentservice.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

	private Long id;

	@NotEmpty(message = "Course code should not be empty")
	@Size(max = 20, message = "Course code length should not exceed {max}")
	private String courseCode;

	@NotEmpty(message = "Name should not be empty")
	@Size(max = 60, message = "Name length should not exceed {max}")
	private String name;

	@NotNull(message = "Credit should not be null")
	@Min(value = 1, message = "Credit should not be less than {value}")
	private Integer credit;

	@NotEmpty(message = "Type should not be empty")
	@Size(max = 20, message = "Type length should not exceed {max}")
	private String type;

	@NotEmpty(message = "Status should not be empty")
	@Size(max = 20, message = "Status length should not exceed {max}")
	private String status;

	@NotNull(message = "Course capacity should not be null")
	@Min(value = 1, message = "Course capacity should not be less than {value}")
	private Integer courseCapacity;

	@NotNull(message = "Number of students should not be null")
	@Min(value = 0, message = "Number of students should not be less than {value}")
	private Integer numberOfStudents;

	@NotNull(message = "isUndergrad should not be null")
	private Boolean isUndergrad;

	@NotNull(message = "Department ID should not be null")
	@Size(max = 60, message = "Department Name length should not exceed {max}")
	private String departmentName;
}
