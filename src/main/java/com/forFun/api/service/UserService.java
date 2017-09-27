package com.forFun.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.forFun.api.dto.CustomUserDetails;
import com.forFun.model.Role;
import com.forFun.model.User;
import com.forFun.model.UserRole;
import com.forFun.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	public List<User> findAll() {
		return repository.findAll();
	}

	public CustomUserDetails getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		return (CustomUserDetails) auth.getPrincipal();
	}

	public Boolean isUsuarioLogadoAdmin() {
		return customUserDetailsService.isAdmin(getLoggedUser().getUsername());
	}

	public User findById(Long userId) {
		return repository.findOne(userId);
	}

	public User findByUserName(String username) {
		return repository.findByUserName(username);
	}

	public User update(User user) {
		return repository.save(user);
	}

	public User create(User user) {
		user.setPassword(getEncryptedPassword(user.getPassword()));
		return repository.save(user);
	}

	public String getEncryptedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public User inativate(User user) {
		user.setEnabled(false);
		return repository.save(user);
	}

	public void changeUserPassword(final User user, final String password) {
		user.setPassword(passwordEncoder.encode(password));
		repository.save(user);
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
		return passwordEncoder.matches(oldPassword, user.getPassword());
	}

	public boolean checkIfValidUserName(String username) {
		User result = repository.findByUserName(username);
		return result == null ? true : false;
	}

	public User getUserByID(final long id) {
		return repository.findOne(id);
	}

	public User createByUserDetails(CustomUserDetails customUserDetails) {
		// clearing old relationships
		List<UserRole> userRoles = userRoleService.findUserId(customUserDetails.getUserid());
		userRoleService.delete(userRoles);

		// creating new ones
		List<String> roles = customUserDetails.getUserRoles();
		for (String name : roles) {
			Role role = roleService.findByName(name);
			if (role == null) {
				// new role?
				role = roleService.save(new Role(name));
			}
			UserRole userRole = new UserRole();
			userRole.setRoleid(role.getId());
			userRole.setUserid(customUserDetails.getUserid());
			userRoleService.save(userRole);
		}

		User user = customUserDetails.getUser();
		return create(user);
	}

}
