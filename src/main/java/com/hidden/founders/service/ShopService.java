package com.hidden.founders.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hidden.founders.model.Shop;
import com.hidden.founders.model.UserDislike;
import com.hidden.founders.repository.IShopRepository;

@Service
	public class ShopService implements IShopService {
		@Autowired
		private IShopRepository shopRepository;
		
		/**
		 * This method returns all existing shops in our collection.
		 * @return List of all shops.
		 * 
		 */

		@Override
		public List<Shop> findAll() {
			return shopRepository.findAll();
		}
		
		/**
		 * This method returns a shop by its id.
		 * @param idShop: id of the shop to return.
		 * @return Instance of the shop.
		 * 
		 */
		
		@Override
		public Shop getOneById(String idShop) {
			return shopRepository.findOne(idShop);
		}
		
		/** 
		 * This method allows a user to like a shop, by adding its id 
		 * the shops' prefferedByUserIds List.
		 * @param idShop: id of the shop to like.
		 * @param idUser: id of the user in question. 
		 * @return the instance of the shop updated.
		 */

		@Override
		public Shop likeShop(String idShop,String idUser) {
			Shop shop=shopRepository.findOne(idShop);
			List<String> likes=shop.getPrefferedByUserIds();
			likes.add(idUser);
			shop.setPrefferedByUserIds(likes);
			return shopRepository.save(shop);
		}
		
		/** 
		 * This method allows a user to dislike a shop, by adding an instance of 
		 * UserDislike in its dislikedByUserIds List.
		 * @param idShop: id of the shop to dislike.
		 * @param idUser: id of the user in question. 
		 * @return the instance of the shop updated.
		 */

		@Override
		public Shop dislikeShop(String idShop,String idUser) {
			Shop shop=shopRepository.findOne(idShop);
			List<UserDislike> dislikes=shop.getDislikedByUserIds();
			dislikes.add(new UserDislike(idUser,new Date()));
			shop.setDislikedByUserIds(dislikes);
			return shopRepository.save(shop);
		}
		
		/** 
		 * This method return the list of id of users who like the shop
		 * in question.
		 * @param idShop: id of the shop.
		 * @return the list of prefferedBy users' id.
		 */

		@Override
		public List<String> getprefferedByUserIds(String idShop) {
			return shopRepository.findOne(idShop).getPrefferedByUserIds();
		}
		
		/** 
		 * This method return the list of id of users who dislike the shop
		 * in question.
		 * @param idShop: id of the shop.
		 * @return the list of dislikedBy users' id.
		 */
		@Override
		public List<UserDislike> getdislikedByUserIds(String idShop) {
			return shopRepository.findOne(idShop).getDislikedByUserIds();
		}

		



	
}
