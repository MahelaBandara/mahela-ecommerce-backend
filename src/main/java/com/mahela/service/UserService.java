package com.mahela.service;

import com.mahela.exceptions.UserException;
import com.mahela.model.User;

public interface UserService {
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
}
