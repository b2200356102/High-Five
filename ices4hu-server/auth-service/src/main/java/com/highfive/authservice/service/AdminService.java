package com.highfive.authservice.service;

import org.springframework.stereotype.Service;

@Service
public class AdminService {

//	@Autowired
//	UserService userService;
//	QUser user = QUser.user;
//
//	@Autowired
//	AdminRepository repository;
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
