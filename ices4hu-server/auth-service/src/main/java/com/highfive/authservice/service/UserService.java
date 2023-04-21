package com.highfive.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.User;
import com.highfive.authservice.repository.UserRepository;
import com.highfive.authservice.utils.exception.UserNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User addUser(User user) {
		return repository.save(user);
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

//	private QUser user = QUser.user;
//
//	@PersistenceContext
//	private EntityManager em;
//
//
//	public List<QUserDTO> getUsers() {
//		JPAQuery<QUserDTO> query = new JPAQuery<>(em);
//		query.select(new QUserDTO(user.id, user.name, user.surname, user.mail, user.role)).from(user);
//		return query.fetch();
//	}
//
//	public UserDTO getUserById(String id) {
//		JPAQuery<QUserDTO> query = new JPAQuery<>(em);
//		UserDTO userDTO = query.select(new QUserDTO(user.id, user.name, user.surname, user.mail, user.role)).from(user)
//				.where(user.id.eq(id)).fetchFirst();
//		return userDTO;
//	}
//
//	public Boolean checkUserPassword(String id, String password) {
//		JPAQuery<String> query = new JPAQuery<>(em);
//		String psw = query.select(user.password).from(user).where(user.id.eq(id)).fetchFirst();
//		return psw.equals(password);
//	}

}
