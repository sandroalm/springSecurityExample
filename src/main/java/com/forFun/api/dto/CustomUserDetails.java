package com.forFun.api.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.forFun.model.User;

public class CustomUserDetails extends User implements UserDetails {

	private static final long serialVersionUID = 1L;
	private List<String> userRoles;
	private boolean enabled = false;
	private User user;

	public CustomUserDetails(User user, List<String> userRoles, Boolean enabled) {
		super(user);
		this.userRoles = userRoles;
		this.enabled = enabled;
		this.user = user;
	}

	public CustomUserDetails() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getUsername() {
		return super.getUserName();
	}

}
