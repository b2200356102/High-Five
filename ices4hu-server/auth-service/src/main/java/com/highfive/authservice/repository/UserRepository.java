package com.highfive.authservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.authservice.model.User;

public interface UserRepository
		extends JpaRepository<User, Long> /* , QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> */ {

	List<User> findByName(String name);

}
