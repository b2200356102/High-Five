package com.highfive.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository repository;

	@Autowired
	private UserService userService;

//	@Autowired
//	UserService userService;
//	QUser user = QUser.user;
//

//	QAdmin admin = QAdmin.admin;
//
//	@PersistenceContext
//	EntityManager em;
//
//
//	public List<Admin> getAdmins() {
//		return repository.findAll();
//	}
//
//	public UserDTO deleteAdmin(String userId) {
//
//		JPAQuery<Integer> query = new JPAQuery<>(em);
//		Integer id = query.select(admin.id).from(admin).where(admin.userId.eq(userId)).fetchFirst();
//		repository.deleteById(id);
//
//		return userService.getUserById(userId);
//	}

}
