package com.user.loginService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.user.loginService.model.User;
import com.user.loginService.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController{

	@Autowired
	private UserService service;

	@GetMapping
	public List<User> getAll() {
		return service.getAll();
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User entity,BindingResult bindingResult) throws Exception{
		return service.create(entity,bindingResult);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) throws Exception{
		try {
			service.login(username,password);
		}catch(Exception e) {
			return new ResponseEntity<String>("Bad Credentials",HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<String>("Login Succesfully Completed",HttpStatus.OK);
	}

	@PutMapping("/{id}/update")
	@ResponseStatus(HttpStatus.OK)
	public User update(@PathVariable(value = "id") long id, @RequestBody User entity) throws Exception{
		return service.update(id, entity);
	}

	@DeleteMapping("/{id}/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") long id) throws Exception {
		service.delete(id);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User get(@PathVariable(value = "id") long id) throws Exception{
		return service.get(id);
	}
}
