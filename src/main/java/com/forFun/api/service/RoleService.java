package com.forFun.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forFun.model.Role;
import com.forFun.repository.RoleRepository;

/**
 * 
 * @author sandro.almeida
 *
 */

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	public List<Role> findAll() {
		return repository.findAll();
	}

	public Role save(Role role) {
		return repository.save(role);
	}

	public Role findById(Long id) {
		return repository.findOne(id);
	}

	public Role findByName(String name) {
		return repository.findByName(name);
		
	}

}
