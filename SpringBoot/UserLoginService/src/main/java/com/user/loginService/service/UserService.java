package com.user.loginService.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.loginService.dao.UserRepository;
import com.user.loginService.model.User;
import com.user.loginService.validator.UserValidator;


@Service
public class UserService{

	@Autowired
	UserRepository dao;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserValidator userValidator;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	public List<User> getAll() {
		return dao.findAll();
	}

	public User create(@RequestBody User entity,Errors errors) throws Exception{

		entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		entity.setLastLoginDate(new Date(System.currentTimeMillis()));

		return dao.save(entity);
	}

	public void login(String username,String password){

		try {

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

			authenticationManager.authenticate(usernamePasswordAuthenticationToken);

			if (usernamePasswordAuthenticationToken.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				logger.debug(String.format("User %s  authenticated and logged in  successfully!", username));
			}
		}catch(Exception e) {
			logger.error("Exception occured : "+ e);
			throw e;
		}

	}

	@Transactional
	public User update(@PathVariable(value = "id") long id, @RequestBody User entity) throws Exception{
		try {
			entity.setLastLoginDate(new Date(System.currentTimeMillis()));
			if(entity.getPassword()!=null) {
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			}
			return dao.save(entity);
		}catch(Exception exception) {
			logger.error("Exception occured : "+ exception);
			throw exception;
		}
	}

	@Transactional
	public void delete(@PathVariable(value = "id") long id) throws Exception{
		try {
			dao.deleteById(id);
		}catch(Exception exception) {
			logger.error("Exception occured : "+ exception);
			throw exception;
		}

	}

	@Transactional
	public User get(@PathVariable(value = "id") long id) throws Exception{

		try {
			User user = dao.getOne(id);
			user.setLastLoginDate(new Date(System.currentTimeMillis()));
			dao.save(user);
			return user;
		}catch(Exception exception) {
			logger.error("Exception occured : "+ exception);
			throw exception;
		}
	}

	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}

}
