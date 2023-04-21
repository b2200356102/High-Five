package com.highfive.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.authservice.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
