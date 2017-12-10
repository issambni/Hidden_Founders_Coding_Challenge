package com.hidden.founders.service;

import java.util.List;

import com.hidden.founders.model.UserAccount;

public interface IUserService {
	
	List<UserAccount> findAll();
	
	UserAccount getOneById(String userId);
	
	UserAccount createUser(UserAccount user);

}
