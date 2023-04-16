package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.model.User;
import com.highfive.authservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);
	}

	public List<User> saveUsers(List<User> users) {
		return repository.saveAll(users);
	}

	public List<User> getUsers() {
		return repository.findAll();
	}

	public User getUserById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<User> getUserByName(String name) {
		return repository.findByName(name);
	}

	public User updateUser(User user) {
		User oldUser = repository.findById(user.getId()).orElse(null);
		oldUser.setName(user.getName());
		return saveUser(oldUser);
	}

	public String deleteUserById(Long id) {
		repository.deleteById(id);
		return "User: " + id + " deleted successfully.";
	}

}
