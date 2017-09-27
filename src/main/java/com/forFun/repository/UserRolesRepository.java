package com.forFun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.forFun.model.UserRole;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {

	@Query("select r.name from UserRole a, User b, Role r where b.userName=?1 and a.userid=b.userId and a.roleid=r.id ")
	public List<String> findRoleByUserName(String username);

	@Query("select r.name from UserRole a, User b, Role r where b.userName=?1 and a.userid=b.userId and a.roleid=r.id and r.name=?2 ")
	public String findRoleByUserNameAndRoleNome(String username, String roleName);

	@Query("select a from UserRole a, User b where b.userId=?1 and a.userid=b.userId ")
	public List<UserRole> findByUserId(Long userId);

}