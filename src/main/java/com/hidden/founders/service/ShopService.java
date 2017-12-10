package com.hidden.founders.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
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
			List<String> likes=shop.getPreferredByUserIds();
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
		public List<String> getPreferredByUserIds(String idShop) {
			return shopRepository.findOne(idShop).getPreferredByUserIds();
		}
		
		/** 
		 * This method return the list of id of users who dislike the shop
		 * in question.
		 * @param idShop: id of the shop.
		 * @return the list of dislikedBy users' id.
		 */
		@Override
		public List<UserDislike> getDislikedByUserIds(String idShop) {
			return shopRepository.findOne(idShop).getDislikedByUserIds();
		}
		
		/** 
		 * This method return the list of id of users who like the shop
		 * in question.
		 * @param idShop: id of the shop.
		 * @return the list of preferredBy users' id.
		 */
		
		@Override
		public List<Shop> getPreferredShopsByUserId(String idUser) {
			return shopRepository.findByPreferredByUserIds(idUser);
		}
		
		/** 
		 * This method return the list of nearby shops, excluding preferred and disliked shops.
		 * @param point: current position of the user.
		 * @param idUser: id of the user in question.
		 * @return the list of near by shops.
		 */
		@Override
		public List<Shop> findNearByShopsByUserId(Point point,String idUser) {
			DateTime dateTime = new DateTime().minusHours(2);
			return shopRepository.findNearByShops(point,idUser, dateTime.toDate());
		}
		
		/** 
		 * This method allows a user to unlike a shop after previously liking it.
		 * @param idShop: id of the shop in question.
		 * @param idUser: id of the user in question.
		 * @return updated instance of the shop.
		 */
		@Override
		public Shop unlikeShop(String idShop, String idUser) {
			Shop shop=shopRepository.findOne(idShop);
			List<String> likes=shop.getPreferredByUserIds();
			likes.remove(idUser);
			shop.setPrefferedByUserIds(likes);
			return shopRepository.save(shop);
		}
		
		/** 
		 * This method return the list of shops that were previously disliked but
		 * surpassed the 2h dislike time frame.
		 * @param idUser: id of the user in question.
		 * @return the list of invalid shops.
		 */
		@Override
		public List<Shop> findInValidDislikedShopByUserIds(String idUser) {
			DateTime dateTime = new DateTime().minusHours(2);
			return shopRepository.findInValidDislikedShopByUserIds(idUser, dateTime.toDate());
		}
		
		/** 
		 * This method removes the user's id from the list of the disliked shops 
		 * that surpassed the 2h dislike time frame.
		 * @param idUser: id of the user in question.
		 * @return the list of invalid shops updated.
		 */
		@Override
		public List<Shop> removeInValidDislikedShopsByUserIds(String idUser){
			List<Shop> shops=findInValidDislikedShopByUserIds(idUser);
			System.out.println("shops: "+shops.toString());
		 	for (Shop item : shops) {
		 		Iterator<UserDislike> iterator = item.getDislikedByUserIds().iterator();
			 		while(iterator.hasNext()){
			 		    UserDislike currentDislike = iterator.next();
			 		   if(currentDislike.getId().equals(idUser)){
			 		        iterator.remove();
			 		    }
			 		}
			 	}
		 	return shopRepository.save(shops);
	 	
		}

	
}
