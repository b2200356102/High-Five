package com.highfive.enrollmentservice.service;

import com.highfive.enrollmentservice.entity.Course;
import com.highfive.enrollmentservice.exception.ResourceNotFoundException;
import com.highfive.enrollmentservice.DTO.CourseDTO;
import com.highfive.enrollmentservice.repository.CourseRepository;
import com.highfive.enrollmentservice.service.impl.CourseServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootApplication
public class CourseServiceTests {


	@Mock
	private CourseRepository courseRepository;

	private CourseServiceImpl courseService;
	private ModelMapper modelMapper;

	private Course course;

	private CourseDTO courseDTO;
	private Long theId;
	@BeforeEach
	public void setup() {
		theId = 1L;
		course = Course.builder()
				.id(1L)
				.courseCode("BBM101")
				.name("Python")
				.credit(1)
				.type("Elective")
				.status("On")
				.courseCapacity(99)
				.numberOfStudents(1)
				.isUndergrad(true)
				.departmentId(1L)
				.build();
		courseService = new CourseServiceImpl(courseRepository, new ModelMapper());
		modelMapper = new ModelMapper();

	}

	@Test
	@DisplayName("Create Course")
	public void givenCourseObject_whenSaveCourse_thenReturnCourseObject() {

		// given
		CourseDTO courseDTO = new CourseDTO(null, course.getCourseCode(), course.getName(), course.getCredit(), course.getType(),course.getStatus(),course.getCourseCapacity(),course.getNumberOfStudents(), course.getIsUndergrad(), "Computer Engineering");


		given(courseRepository.findCourseById(course.getId())).willReturn(Optional.of(course));

		// when
		when(courseRepository.save(Mockito.any(Course.class))).thenReturn(course);
		CourseDTO savedCourse = courseService.createCourse(courseDTO);

		// then
		Assertions.assertThat(savedCourse).isNotNull();
		Assertions.assertThat(savedCourse.getId()).isEqualTo(1L);

	}

	@Test
	@DisplayName("Create Course with Missing Attribute")
	public void givenCourseObjectWithMissingAttribute_whenSaveCourse_thenThrowException() {
		// given
		CourseDTO courseDTO = new CourseDTO(null, course.getCourseCode(), course.getName(), null, course.getType(),course.getStatus(),course.getCourseCapacity(),course.getNumberOfStudents(), course.getIsUndergrad(), "Computer Engineering");

		// when/then
		Assertions.assertThatThrownBy(() -> courseService.createCourse(courseDTO))
				.isInstanceOf(NullPointerException.class);
	}

	@Test
	@DisplayName("Delete Course")
	public void givenCourseObject_whenDeleteCourse_thenReturnNothing() {
		// given
		given(courseRepository.save(course)).willReturn(course);
		courseRepository.save(course);
		given(courseRepository.findCourseById(course.getId())).willReturn(Optional.of(course)).willReturn(Optional.empty());

		// when
		courseService.deleteCourse(course.getId());

		// Then
		org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			courseService.getCourseById(1L);
		});
	}

	@Test
	@DisplayName("Delete Non-Existing Course")
	public void givenCourseObject_whenDeleteNotExistingCourse_thenReturnNothing() {
		// given
		given(courseRepository.save(course)).willReturn(course);
		courseRepository.save(course);

		// when
		Long id = 3L;
		given(courseRepository.findCourseById(id)).willReturn(Optional.empty());

		// Then
		org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			courseService.deleteCourse(id);
		});
	}


	@Test
	@DisplayName("Find course by id")
	public void givenCourseObject_whenGetCourse_thenReturnCourse() {
		// given
		given(courseRepository.save(course)).willReturn(course);
		Course savedCourse = courseRepository.save(course);

		// when
		given(courseRepository.findCourseById(savedCourse.getId())).willReturn(Optional.ofNullable(course));
		CourseDTO courseDTO = courseService.getCourseById(savedCourse.getId());

		// Then
		Assertions.assertThat(courseDTO).isNotNull();
	}

	@Test
	@DisplayName("Find not existing course by Id")
	public void givenNonExistingId_whenGetCourse_thenReturnError() {
		// given
		Long id = 2L;
		given(courseRepository.save(course)).willReturn(course);
		courseRepository.save(course);

		// when
		org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			courseService.getCourseById(id);
		});


	}
}
