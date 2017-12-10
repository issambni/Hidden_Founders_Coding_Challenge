package com.hidden.founders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hidden.founders.model.UserAccount;

public interface IUserRepository  extends MongoRepository<UserAccount,String>{

}
