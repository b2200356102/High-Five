package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Admin;
import com.highfive.authservice.entity.QAdmin;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.repository.AdminRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AdminService {

	@Autowired
	private AdminRepository repository;
	private QAdmin admin = QAdmin.admin;

	@PersistenceContext
	EntityManager em;

	public Admin addAdmin(User user) {
		Admin newAdmin = new Admin(user.getId());
		return repository.save(newAdmin);

	}

	public List<Admin> getAdmins() {
		return repository.findAll();
	}

	public void removeAdmin(String id) {
		repository.deleteById(id);
	}

}
