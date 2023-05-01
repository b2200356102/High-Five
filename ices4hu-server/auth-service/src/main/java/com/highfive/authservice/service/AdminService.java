package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Admin;
import com.highfive.authservice.entity.QAdmin;
import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.repository.AdminRepository;
import com.highfive.authservice.utils.exception.AdminNotFoundException;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class AdminService {

	@Autowired
	private AdminRepository repository;
	private QAdmin admin = QAdmin.admin;

	@Autowired
	private UserService userService;
	private QUser user = QUser.user;

	@PersistenceContext
	EntityManager em;

	@Transactional
	public Admin addAdmin(User user) {
		userService.addUser(user);
		Admin newAdmin = new Admin(user.getId());
		return repository.save(newAdmin);

	}

	public List<Admin> getAdmins() {
		return repository.findAll();
	}

	public Admin getAdminById(String userId) throws AdminNotFoundException {
		JPAQuery<Admin> query = new JPAQuery<>(em);
		Admin adminResponse = query.select(admin).from(admin)
				.where(admin.userId.eq(userId)).fetchFirst();
		if (adminResponse == null)
			throw new AdminNotFoundException();
		return adminResponse;
	}

	public void removeAdmin(String id) {
		repository.deleteById(id);
	}

}
