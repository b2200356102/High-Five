package com.highfive.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.Admin;
import com.highfive.authservice.entity.dto.AdminDTO;
import com.highfive.authservice.repository.AdminRepository;
import com.highfive.authservice.utils.exception.UserNotFoundException;

@Service
public class AdminService {

	@Autowired
	private AdminRepository repository;

	@Autowired
	private UserService userService;

	public Admin setAdmin(AdminDTO adminDTO) throws UserNotFoundException {
		userService.setUser(adminDTO.getUser());
		return repository.save(new Admin(null, adminDTO.getUser().getId()));
	}

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
