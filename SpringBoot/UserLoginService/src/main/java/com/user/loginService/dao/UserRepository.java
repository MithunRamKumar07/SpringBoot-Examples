package com.user.loginService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.loginService.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

	User findByUsername(String username);

}
