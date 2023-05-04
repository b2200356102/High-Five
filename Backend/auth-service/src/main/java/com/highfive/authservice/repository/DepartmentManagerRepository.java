package com.highfive.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.authservice.entity.DepartmentManager;

public interface DepartmentManagerRepository
		extends JpaRepository<DepartmentManager, Integer> {

}
