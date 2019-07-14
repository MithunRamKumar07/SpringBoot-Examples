package com.user.loginService.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.user.loginService.model.User;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserDetailsService userService;

	private static final String NOT_EMPTY="This field is required.";
	private static final String USERNAME_SIZE="Please use between 6 and 32 characters.";
	private static final String USERNAME_DUPLICATE="Someone already has that username.";
	private static final String PASSWORD="Try one with at least 8 characters.";

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", NOT_EMPTY);
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
			errors.rejectValue("username", USERNAME_SIZE);
		}
		if (userService.loadUserByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", USERNAME_DUPLICATE);
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}
	}
}
