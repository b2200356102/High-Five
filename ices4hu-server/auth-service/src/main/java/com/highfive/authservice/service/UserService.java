package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.QUserDTO;
import com.highfive.authservice.entity.dto.UserDTO;
import com.highfive.authservice.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	private QUser user = QUser.user;

	@PersistenceContext
	private EntityManager em;

	public User createUser(User userRequest) {
		return repository.save(userRequest);
	}

	public List<QUserDTO> getUsers() {
		JPAQuery<QUserDTO> query = new JPAQuery<>(em);
		query.select(new QUserDTO(user.id, user.name, user.surname, user.mail, user.role)).from(user);
		return query.fetch();
	}

	public UserDTO getUserById(String id) {
		JPAQuery<QUserDTO> query = new JPAQuery<>(em);
		UserDTO userDTO = query.select(new QUserDTO(user.id, user.name, user.surname, user.mail, user.role)).from(user)
				.where(user.id.eq(id)).fetchFirst();
		return userDTO;
	}

	public Boolean checkUserPassword(String id, String password) {
		JPAQuery<String> query = new JPAQuery<>(em);
		String psw = query.select(user.password).from(user).where(user.id.eq(id)).fetchFirst();
		return psw.equals(password);
	}

}
