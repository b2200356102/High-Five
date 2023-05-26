package com.highfive.authservice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

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

	@PersistenceContext
	private EntityManager em;

	@Autowired
	SendGrid sendGrid;

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
		return query.select(
				new QUserDTO(user.id, user.name, user.surname, user.mail, user.pending, user.role))
				.from(user).fetch();
	}

	public List<UserDTO> getPendingUsers() {
		JPAQuery<UserDTO> query = new JPAQuery<>(em);
		return query.select(
				new QUserDTO(user.id, user.name, user.surname, user.mail, user.pending, user.role))
				.from(user).where(user.pending).fetch();
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
			return query.select(new QUserDTO(user.id, user.name, user.surname, user.mail,
					user.pending, user.role)).from(user).where(user.id.eq(id)).fetchFirst();
		}
	}

	public User setUser(UserDTO user) throws UserNotFoundException {
		User newUser = getUserById(user.getId());

		if (user.getName() != null)
			newUser.setName(user.getName());
		if (user.getSurname() != null)
			newUser.setSurname(user.getSurname());
		if (user.getMail() != null)
			newUser.setMail(user.getMail());
		if (user.getPending() != null)
			newUser.setPending(user.getPending());

		return repository.save(newUser);
	}

	public User setPassword(String id, String newPassword) throws UserNotFoundException {
		User user = getUserById(id);
		user.setPassword(PasswordManager.encode(newPassword));
		return repository.save(user);
	}

	public void resetUserPassword(String id) throws UserNotFoundException {
		User user = getUserById(id);
		String newPassword = PasswordManager.generateNewPassword();
		user.setPassword(newPassword);
		repository.save(user);

		String htmlValue = "<div style=\"font-family: inherit; text-align: inherit\">Your password has been changed successfully!</div>\n"
				+ "<div style=\"font-family: inherit; text-align: inherit; margin-left: 0px\"><span style=\"box-sizing: border-box; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px; margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-style: inherit; font-variant-ligatures: inherit; font-variant-caps: inherit; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-variant-alternates: inherit; font-weight: inherit; font-stretch: inherit; line-height: inherit; font-family: inherit; font-optical-sizing: inherit; font-kerning: inherit; font-feature-settings: inherit; font-variation-settings: inherit; font-size: 14px; vertical-align: baseline; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; border-top-style: initial; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-top-color: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-image-source: initial; border-image-slice: initial; border-image-width: initial; border-image-outset: initial; border-image-repeat: initial; color: #000000; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: pre-wrap; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial\">If this wasn't you, your account has been compromised. Please contact to your supervisor.</span>&nbsp;</div>\n"
				+ "<div style=\"font-family: inherit; text-align: inherit\"><br></div>\n"
				+ "<div style=\"font-family: inherit; text-align: inherit\">Your new password is:</div>"
				+ "<div style=\"font-family: inherit; text-align: inherit\"><span style=\"color: #000000; font-family: arial, helvetica, sans-serif; font-size: 32px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: pre-wrap; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline\">"
				+ newPassword + "</span>&nbsp;</div>";

		Email from = new Email("infofivehigh@gmail.com");
		Email to = new Email(user.getMail());
		String subject = "Your password has changed!";
		Content content = new Content("text/html", htmlValue);

		Mail mail = new Mail(from, subject, to, content);

		Request request = new Request();

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sendGrid.api(request);
		} catch (Exception e) {
			// TODO: handle exception
		}

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
		throw new UserNotFoundException();
	}

}
