package com.highfive.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.authservice.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
