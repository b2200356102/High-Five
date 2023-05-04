package com.highfive.authservice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.highfive.authservice.entity.DepartmentManager;
import com.highfive.authservice.entity.QUser;
import com.highfive.authservice.entity.User;
import com.highfive.authservice.entity.dto.QUserDTO;
import com.highfive.authservice.entity.dto.UserDTO;
import com.highfive.authservice.repository.UserRepository;
import com.highfive.authservice.utils.PasswordManager;
import com.highfive.authservice.utils.exception.DepartmentNotFoundException;
import com.highfive.authservice.utils.exception.UserAlreadyExistsException;
import com.highfive.authservice.utils.exception.UserNotFoundException;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	private QUser user = QUser.user;

	@Inject
	@Lazy
	private AdminService adminService;

	@Inject
	@Lazy
	private DepartmentManagerService departmentManagerService;

	@Inject
	@Lazy
	private InstructorService instructorService;

	@Inject
	@Lazy
	private StudentService studentService;

	@Autowired
	private JavaMailSender mailSender;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Object addUser(User user, Integer departmentId) throws UserAlreadyExistsException {

		try {
			User u = getUserById(user.getId());
			throw new UserAlreadyExistsException();
		} catch (UserNotFoundException e) {
			user.setPassword(PasswordManager.encode(user.getPassword()));

			switch (user.getRole()) {
			case "admin":
				adminService.addAdmin(user);
				break;
			case "department_manager":
				instructorService.addInstructor(user, departmentId);
				DepartmentManager dm = new DepartmentManager(null, user.getId(), departmentId);
				departmentManagerService.addDepartmentManager(dm);
				break;
			case "instructor":
				instructorService.addInstructor(user, departmentId);
				break;
			case "student":
				studentService.addStudent(user, departmentId);
				break;
			default:
				throw new IllegalArgumentException(
						"Unexpected role for new user: " + user.getRole());
			}

			return repository.save(user);
		}
	}

	public List<User> getUsers() {
		return repository.findAll();
	}

	public List<UserDTO> getUserDTOs() {
		JPAQuery<UserDTO> query = new JPAQuery<>(em);
		return query.select(new QUserDTO(user.id, user.name, user.mail, user.pending)).from(user)
				.fetch();
	}

	public User getUserById(String id) throws UserNotFoundException {
		User user = repository.findById(id).orElse(null);
		if (user == null)
			throw new UserNotFoundException();
		return user;
	}

	public Object getUserDTOById(String id)
			throws UserNotFoundException, DepartmentNotFoundException {
		User u = getUserById(id);

		switch (u.getRole()) {
		case "instructor":
		case "department_manager":
			return instructorService.getInstructorDTOById(id);
		case "student":
			return studentService.getStudentDTOById(id);
		default:
			JPAQuery<UserDTO> query = new JPAQuery<>(em);
			return query.select(new QUserDTO(user.id, user.name, user.mail, user.pending))
					.from(user).where(user.id.eq(id)).fetchFirst();
		}
	}

	public User setUser(UserDTO user) throws UserNotFoundException {
		User newUser = getUserById(user.getId());

		if (user.getName() != null)
			newUser.setName(user.getName());
		if (user.getMail() != null)
			newUser.setMail(user.getMail());
		if (user.getPending() != null)
			newUser.setPending(user.getPending());

		return repository.save(newUser);
	}

	public void setUserPassword(String id) throws UserNotFoundException {
		User user = getUserById(id);
		String newPassword = PasswordManager.generateNewPassword();
		user.setPassword(newPassword);
		repository.save(user);

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("test@gmail.com");
		msg.setTo(user.getMail());
		msg.setText("Your password has been changed to:\n" + newPassword);
		msg.setSubject("Your password has been changed");
		mailSender.send(msg);
	}

	@Transactional
	public void removeUserById(String id)
			throws UserNotFoundException, DepartmentNotFoundException {
		User user = getUserById(id);

		switch (user.getRole()) {
		case "admin":
			adminService.removeAdmin(id);
			break;
		case "department_manager":
			departmentManagerService.removeDepartmentManager(id);
			instructorService.removeInstructorById(id);
			break;
		case "instructor":
			instructorService.removeInstructorById(id);
			break;
		case "student":
			studentService.removeStudentById(id);
			break;
		default:
			throw new IllegalArgumentException("Unexpected role for user: " + user.getRole());
		}
		repository.deleteById(id);
	}

	public Object checkPassword(String id, String password)
			throws UserNotFoundException, DepartmentNotFoundException {
		User user = getUserById(id);
		if (PasswordManager.match(user.getPassword(), password))
			return getUserDTOById(id);
		return new User();
	}

}
