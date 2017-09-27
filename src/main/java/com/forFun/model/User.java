package com.forFun.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid")
	private Long userId;

	@Column(name = "username")
	private String userName;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private Boolean enabled = false;

	public User() {

	}

	public User(User user) {
		this.userId = user.userId;
		this.userName = user.userName;
		this.password = user.password;
		this.enabled = user.enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserid() {
		return userId;
	}

	public void setUserid(Long userid) {
		this.userId = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}