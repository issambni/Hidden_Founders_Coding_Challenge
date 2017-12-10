package com.hidden.founders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hidden.founders.model.UserAccount;
import com.hidden.founders.repository.IUserRepository;

@Service
public class UserService implements IUserService {
		@Autowired
		private IUserRepository userRepository;
		
		/**
		 * This method returns all existing users in our user collection.
		 * @return List of all shops.
		 * 
		 */
		
		@Override
		public List<UserAccount> findAll() {
			return userRepository.findAll();
		}
		
		/**
		 * This method returns a user by its id.
		 * @param idUser: id of the user to return.
		 * @return Instance of the user.
		 * 
		 */

		@Override
		public UserAccount getOneById(String userId) {
			return userRepository.findOne(userId);
		}
		
		/**
		 * This method creates a new user entry in our collection.
		 * @param user: instance of a user.
		 * @return Instance of the created user.
		 * 
		 */
		
		@Override
		public UserAccount createUser(UserAccount user) {
			return userRepository.save(user);
		}

		
		

}
