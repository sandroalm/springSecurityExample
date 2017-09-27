/**
 * 
 */
package com.forFun.api.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.forFun.api.dto.CustomUserDetails;
import com.forFun.api.service.CustomUserDetailsService;
import com.forFun.api.service.UserService;
import com.forFun.model.User;

/**
 * @author sandro.almeida
 *
 */

@RestController
@RequestMapping("/api")
public class UserController {

	private static final String APPLICATION_JSON = "application/json";

	@Autowired
	private UserService userService;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@RequestMapping(value = "/users/current", method = GET, produces = { APPLICATION_JSON })
	public ResponseEntity<CustomUserDetails> findCurrentUser() {
		return new ResponseEntity<CustomUserDetails>(userService.getLoggedUser(), OK);
	}

	@RequestMapping(value = "/users/details/{username}", method = GET, produces = { APPLICATION_JSON })
	public ResponseEntity<CustomUserDetails> findUserDetail(@PathVariable("username") String username) {
		CustomUserDetails result = customUserDetailsService.loadUserCustomUserDetails(username);
		return new ResponseEntity<CustomUserDetails>(result, OK);

	}

	@RequestMapping(value = "/users", method = GET, produces = { APPLICATION_JSON })
	public ResponseEntity<List<User>> getAll() {
		return new ResponseEntity<List<User>>(userService.findAll(), OK);
	}

	@RequestMapping(value = "/users/{id}", method = PUT, consumes = { APPLICATION_JSON }, produces = { APPLICATION_JSON })
	@ResponseStatus(value = OK)
	public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody CustomUserDetails user, HttpServletRequest request, HttpServletResponse response) {
		if (!id.equals(user.getUserid())) {
			return new ResponseEntity<>(BAD_REQUEST);
		} else {
			userService.createByUserDetails(user);
			return new ResponseEntity<User>(OK);
		}
	}

	@RequestMapping(value = "/users/{id}", method = DELETE, produces = { APPLICATION_JSON })
	public ResponseEntity<User> inactivate(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
		CustomUserDetails logado = userService.getLoggedUser();
		// only ADMINS should inactivate
		Boolean hasRole = customUserDetailsService.hasRole(logado.getUsername(), "ROLE_ADMIN");
		if (hasRole) {
			User user = userService.findById(id);
			if (!id.equals(user.getUserid())) {
				return new ResponseEntity<>(BAD_REQUEST);
			} else {
				return new ResponseEntity<User>(userService.inativate(user), OK);
			}
		} else {
			return new ResponseEntity<User>(UNAUTHORIZED);
		}
	}
}
