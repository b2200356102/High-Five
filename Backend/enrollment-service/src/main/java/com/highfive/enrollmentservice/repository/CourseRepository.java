package com.highfive.enrollmentservice.repository;

import com.highfive.enrollmentservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findCourseById(Long id);

	List<Course> findAllByDepartmentId(Long departmentId);
}
