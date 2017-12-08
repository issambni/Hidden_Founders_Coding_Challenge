package com.hidden.founders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hidden.founders.model.User;

public interface IUserRepository  extends MongoRepository<User,String>{

}
