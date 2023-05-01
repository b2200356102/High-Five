package com.highfive.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.authservice.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, String> {

}
