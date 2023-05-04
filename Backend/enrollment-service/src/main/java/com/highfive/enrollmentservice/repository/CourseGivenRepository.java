package com.highfive.enrollmentservice.repository;

import com.highfive.enrollmentservice.entity.CourseGiven;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseGivenRepository extends JpaRepository<CourseGiven, Long> {
	Optional<CourseGiven> findCourseGivenById(Long courseGivenId);
}
