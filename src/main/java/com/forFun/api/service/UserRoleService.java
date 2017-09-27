package com.forFun.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forFun.model.UserRole;
import com.forFun.repository.UserRolesRepository;

/**
 * 
 * @author sandro.almeida
 *
 */

@Service
public class UserRoleService {

	@Autowired
	private UserRolesRepository repository;

	public List<UserRole> findAll() {
		return repository.findAll();
	}

	public UserRole save(UserRole role) {
		return repository.save(role);
	}

	public UserRole findById(Long id) {
		return repository.findOne(id);
	}

	public List<UserRole> findUserId(Long userId) {
		return repository.findByUserId(userId);
	}

	public void delete(List<UserRole> userRoles) {
		repository.delete(userRoles);
	}

}
