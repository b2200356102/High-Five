package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.repository.UserRepository;
import com.highfive.authservice.utils.PasswordManager;
import com.highfive.authservice.utils.exception.UserNotFoundException;

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
		return repository.save(user);
	}

	public List<User> getUsers() {
		return repository.findAll();
	}

	public User getUserById(String id) throws UserNotFoundException {
		User user = repository.findById(id).orElse(null);
		if (user == null)
			throw new UserNotFoundException();
		return user;
	}

	public String getUserPasswordById(String id) throws UserNotFoundException {
		User user = getUserById(id);
		return user.getPassword();
	}

	public User setUser(User user) throws UserNotFoundException {
		User newUser = repository.findById(user.getId()).orElse(null);
		if (newUser == null)
			throw new UserNotFoundException();

		if (user.getName() != null)
			newUser.setName(user.getName());
		if (user.getSurname() != null)
			newUser.setSurname(user.getSurname());
		if (user.getPassword() != null)
			newUser.setPassword(user.getPassword());
		if (user.getMail() != null)
			newUser.setMail(user.getMail());
		if (user.getRole() != null)
			newUser.setRole(user.getRole());
		if (user.getIsPending() != null)
			newUser.setIsPending(user.getIsPending());

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
