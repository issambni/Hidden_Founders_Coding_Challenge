package com.hidden.founders.service;

import java.util.List;

import com.hidden.founders.model.User;

public interface IUserService {
	
	List<User> findAll();
	
	User getOneById(String userId);
	
	User createUser(User user);

}
