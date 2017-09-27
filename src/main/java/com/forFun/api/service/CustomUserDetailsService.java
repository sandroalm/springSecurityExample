package com.forFun.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.forFun.api.dto.CustomUserDetails;
import com.forFun.model.User;
import com.forFun.repository.UserRepository;
import com.forFun.repository.UserRolesRepository;

/**
 * 
 * @author sandro.almeida
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	private final UserRolesRepository userRolesRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository, UserRolesRepository userRolesRepository) {
		this.userRepository = userRepository;
		this.userRolesRepository = userRolesRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + username);
		} else {
			List<String> userRoles = userRolesRepository.findRoleByUserName(username);
			return new CustomUserDetails(user, userRoles, user.getEnabled());
		}
	}

	public CustomUserDetails loadUserCustomUserDetails(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + username);
		} else {
			List<String> userRoles = userRolesRepository.findRoleByUserName(username);
			return new CustomUserDetails(user, userRoles, user.getEnabled());
		}
	}

	public Boolean hasRole(String username, String roleName) {
		String result = userRolesRepository.findRoleByUserNameAndRoleNome(username, roleName);
		return result != null && !result.trim().isEmpty();
	}

	public Boolean isAdmin(String username) {
		String result = userRolesRepository.findRoleByUserNameAndRoleNome(username, "ROLE_ADMIN");
		return result != null && !result.trim().isEmpty();
	}
}
