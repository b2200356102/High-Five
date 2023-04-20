package com.highfive.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.authservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
