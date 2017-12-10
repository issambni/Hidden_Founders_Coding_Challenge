package com.hidden.founders.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.hidden.founders.model.Shop;

public interface IShopRepository extends MongoRepository<Shop,String>{
	
	/**
	 * This method finds preferred shops by user's id.
	 * @param idUser: user's id
	 * @return List of shops
	 */
	
	List<Shop> findByPreferredByUserIds(String idUser);
	/**
	 * This method returns nearby shops, that are not preferred by the user 
	 * having a difference time between the now and last disliked time exceeds two hours.
	 * It also sorts the results by position.
	 * @param a: Point, representing user's position
	 * @param idUser: user's id
	 * @param date: Date, difference between current date and two hours ago
	 * @return List of shops.
	 */
	@Query("{location:{$nearSphere :?0},'prefferedByUserIds' : {$ne : ?1},dislikedByUserIds:{$not:{$elemMatch:{dislikedAt : {$gt : ?2}, id: ?1}}}}")
	List<Shop> findNearByShops(Point a, String idUser, Date date); // date is the difference between current date and two hours ago

	/**
	 * This method sorts shops regardless of their like status by a user.
	 * @param a: Point, representing user's position
	 * @return List of shops
	 */
	@Query("{ location:{$nearSphere :?0}}")
	List<Shop> sortByDistance(Point a ); 
	
	@Query("{dislikedByUserIds:{$elemMatch: {dislikedAt : {$lt : ?1}, id: ?0} } }")
	List<Shop> findInValidDislikedShopByUserIds(String idUser, Date date); // date is the difference between current date and two hours ago
}
