package com.highfive.authservice.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

//	@Autowired
//	private UserRepository repository;
//	private QUser user = QUser.user;
//
//	@PersistenceContext
//	private EntityManager em;
//
//	public User createUser(User userRequest) {
//		return repository.save(userRequest);
//	}
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
