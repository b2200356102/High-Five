package com.highfive.authservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.authservice.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	public List<User> findByMail(String mail);
}
