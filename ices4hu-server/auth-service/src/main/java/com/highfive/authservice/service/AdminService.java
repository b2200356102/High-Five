package com.highfive.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highfive.authservice.repository.AdminRepository;
import com.highfive.authservice.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository repository;

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	UserService userService;
//	QUser user = QUser.user;
//

//	QAdmin admin = QAdmin.admin;
//
//	@PersistenceContext
//	EntityManager em;
//
//	@Transactional
//	public Admin addAdmin(User user) {
//		userService.createUser(user);
//		// Admin newAdmin = new Admin(null, user.getId());
//		return null; // repository.save(newAdmin);
//	}
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
