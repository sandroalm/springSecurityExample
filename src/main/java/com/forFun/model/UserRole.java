package com.forFun.model;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
public class UserRole {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name="user_role_id")
	private Long userroleid;
	
	@Column(name="userid")
	private Long userid;
	
	@Column(name="roleid")
	private Long roleid;	

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getUserroleid() {
		return userroleid;
	}

	public void setUserroleid(Long userroleid) {
		this.userroleid = userroleid;
	}	
	
}
