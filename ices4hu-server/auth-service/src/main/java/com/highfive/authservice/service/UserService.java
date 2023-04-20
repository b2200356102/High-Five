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

		if (userRequest.getId() == null)
			throw new RuntimeException("Id field is empty");
		if (repository.findById(userRequest.getId()).orElse(null) != null)
			throw new RuntimeException("User with id " + userRequest.getId() + " already exists");
		if (userRequest.getName() == null)
			throw new RuntimeException("Name field is empty");
		if (userRequest.getSurname() == null)
			throw new RuntimeException("Surname field is empty");
		if (userRequest.getMail() == null)
			throw new RuntimeException("Mail field is empty");

		JPAQuery<String> query = new JPAQuery<>(em);
		if (query.select(user.mail).from(user).where(user.mail.eq(userRequest.getMail())).fetch() != null)
			throw new RuntimeException("User with mail address " + userRequest.getMail() + " already exists");

		if (userRequest.getStatus() == null)
			userRequest.setStatus(false);

		return repository.save(userRequest);
	}

	public List<QUserDTO> getUsers() {
		JPAQuery<QUserDTO> query = new JPAQuery<>(em);
		query.select(new QUserDTO(user.id, user.name, user.surname, user.mail, user.role)).from(user);
		return query.fetch();
	}

	public UserDTO getUserById(Integer id) {
		JPAQuery<QUserDTO> query = new JPAQuery<>(em);
		UserDTO userDTO = query.select(new QUserDTO(user.id, user.name, user.surname, user.mail, user.role)).from(user)
				.where(user.id.eq(id)).fetchFirst();
		return userDTO;
	}

	public Boolean checkUserPassword(Integer id, String password) {
		JPAQuery<String> query = new JPAQuery<>(em);
		String psw = query.select(user.password).from(user).where(user.id.eq(id)).fetchFirst();
		return psw.equals(password);
	}

}
