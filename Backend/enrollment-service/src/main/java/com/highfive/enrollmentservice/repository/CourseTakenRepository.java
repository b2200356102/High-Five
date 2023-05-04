package com.highfive.enrollmentservice.repository;

import com.highfive.enrollmentservice.entity.CourseTaken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseTakenRepository extends JpaRepository<CourseTaken, Long> {

	Optional<CourseTaken> findCourseTakenById(Long courseTakenId);
}
