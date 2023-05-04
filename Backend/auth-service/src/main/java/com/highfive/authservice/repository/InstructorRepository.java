package com.highfive.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.authservice.entity.Lecturer;

public interface InstructorRepository extends JpaRepository<Lecturer, String> {

}
