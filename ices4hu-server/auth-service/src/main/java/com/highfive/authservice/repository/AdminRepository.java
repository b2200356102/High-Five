package com.highfive.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.authservice.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

}
