package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Admin;
import com.highfive.authservice.entity.QAdmin;
import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.UserDTO;
import com.highfive.authservice.repository.AdminRepository;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class AdminService {

	@Autowired
	UserService userService;
	QUser user = QUser.user;

	@Autowired
	AdminRepository repository;
	QAdmin admin = QAdmin.admin;

	@PersistenceContext
	EntityManager em;

	@Transactional
	public Admin addAdmin(User user) {
		userService.createUser(user);
		Admin newAdmin = new Admin(null, user.getId());
		return repository.save(newAdmin);
	}

	public List<Admin> getAdmins() {
		return repository.findAll();
	}

	public UserDTO deleteAdmin(String userId) {

		JPAQuery<Integer> query = new JPAQuery<>(em);
		Integer id = query.select(admin.id).from(admin).where(admin.userId.eq(userId)).fetchFirst();
		repository.deleteById(id);

		return userService.getUserById(userId);
	}

}
