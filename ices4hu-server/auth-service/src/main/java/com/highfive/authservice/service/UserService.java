package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.QUserDTO;
import com.highfive.authservice.entity.dto.UserDTO;
import com.highfive.authservice.repository.UserRepository;
import com.highfive.authservice.utils.PasswordManager;
import com.highfive.authservice.utils.exception.UserNotFoundException;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	private QUser user = QUser.user;

	@Autowired
	private JavaMailSender mailSender;

	@PersistenceContext
	private EntityManager em;

	public User addUser(User user) {
		user.setPassword(PasswordManager.encode(user.getPassword()));
		return repository.save(user);
	}

	public List<User> getUsers() {
		return repository.findAll();
	}

	public List<UserDTO> getUserDTOs() {
		JPAQuery<UserDTO> query = new JPAQuery<>(em);
		return query.select(new QUserDTO(user.id, user.name, user.mail, user.pending))
				.from(user).fetch();
	}

	public User getUserById(String id) throws UserNotFoundException {
		User user = repository.findById(id).orElse(null);
		if (user == null)
			throw new UserNotFoundException();
		return user;
	}

	public UserDTO getUserDTOById(String id) {
		JPAQuery<UserDTO> query = new JPAQuery<>(em);
		return query.select(new QUserDTO(user.id, user.name, user.mail, user.pending))
				.from(user).where(user.id.eq(id)).fetchFirst();
	}

	public Boolean checkPassword(String id, String password)
			throws UserNotFoundException {
		User user = getUserById(id);
		return PasswordManager.match(user.getPassword(), password);
	}

	public User setUser(UserDTO user) throws UserNotFoundException {
		User newUser = repository.findById(user.getId()).orElse(null);
		if (newUser == null)
			throw new UserNotFoundException();

		if (user.getName() != null)
			newUser.setName(user.getName());
		if (user.getMail() != null)
			newUser.setMail(user.getMail());
		if (user.getPending() != null)
			newUser.setPending(user.getPending());

		return repository.save(newUser);
	}

	public void setUserPassword(String id) throws UserNotFoundException {
		User user = getUserById(id);
		String newPassword = PasswordManager.generateNewPassword();
		user.setPassword(newPassword);
		repository.save(user);

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("test@gmail.com");
		msg.setTo(user.getMail());
		msg.setText("Your password has been changed to:\n" + newPassword);
		msg.setSubject("Your password has been changed");
		mailSender.send(msg);
	}

	public void removeUserById(String id) {
		repository.deleteById(id);
	}

}
