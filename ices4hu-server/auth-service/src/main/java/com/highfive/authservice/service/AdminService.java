package com.highfive.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Admin;
import com.highfive.authservice.entity.QAdmin;
import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.dto.AdminDTO;
import com.highfive.authservice.entity.dto.QAdminDTO;
import com.highfive.authservice.repository.AdminRepository;
import com.highfive.authservice.utils.exception.AdminNotFoundException;
import com.highfive.authservice.utils.exception.UserNotFoundException;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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

	public List<Admin> getAdmins() {
		return repository.findAll();
	}

	public List<AdminDTO> getAdminDTOs() {
		JPAQuery<AdminDTO> query = new JPAQuery<>(em);
		query.select(new QAdminDTO(admin.id, user)).from(admin).innerJoin(user)
				.on(admin.userId.eq(user.id));
		return query.fetch();
	}

	public Admin getAdminById(Integer id) throws AdminNotFoundException {
		Admin admin = repository.findById(id).orElse(null);
		if (admin == null)
			throw new AdminNotFoundException();
		return admin;
	}

	public AdminDTO getAdminDTOById(Integer id) throws AdminNotFoundException {
		JPAQuery<AdminDTO> query = new JPAQuery<>(em);
		AdminDTO adminDTO = query.select(new QAdminDTO(admin.id, user)).from(admin)
				.innerJoin(user).on(admin.userId.eq(user.id)).where(admin.id.eq(id))
				.fetchFirst();
		if (adminDTO == null)
			throw new AdminNotFoundException();
		return adminDTO;
	}

	public Admin getAdminByUserId(String userId) throws AdminNotFoundException {
		JPAQuery<Admin> query = new JPAQuery<>(em);
		Admin adminResponse = query.select(admin).from(admin)
				.where(admin.userId.eq(userId)).fetchFirst();
		if (adminResponse == null)
			throw new AdminNotFoundException();
		return adminResponse;
	}

	public AdminDTO getAdminDTOByUserId(String userId) throws AdminNotFoundException {
		Admin admin = getAdminByUserId(userId);
		if (admin == null)
			throw new AdminNotFoundException();
		return getAdminDTOById(admin.getId());
	}

	public Admin setAdmin(AdminDTO adminDTO) throws UserNotFoundException {
		userService.setUser(adminDTO.getUser());
		return repository.save(new Admin(null, adminDTO.getUser().getId()));
	}

	public void removeAdmin(Integer id) {
		repository.deleteById(id);
	}

	public void removeAdminByUserId(String userId) throws AdminNotFoundException {
		Admin admin = getAdminByUserId(userId);
		repository.deleteById(admin.getId());
	}

}
