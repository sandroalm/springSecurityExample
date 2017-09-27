/**
 * 
 */
package com.forFun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forFun.model.Role;

/**
 * @author sandro.almeida
 *
 */
public interface  RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);

}
