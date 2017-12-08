package com.hidden.founders.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.hidden.founders.model.Shop;

public interface IShopRepository extends MongoRepository<Shop,String>{
	
	List<Shop> findByPrefferedByUserIds(String id);
	
	@Query("{location:{$nearSphere :?0},'prefferedByUserIds' : {$ne : ?1},'$or': [{'dislikedByUserIds.id': {$ne: ?1}},{'dislikedByUserIds.dislikedAt' : {$lt : ?2}, 'dislikedByUserIds.id': ?1}]}")
	List<Shop> findByPrefferedByUserIdsNot(Point a,String id, Date date); // date is the difference between current date and two hours ago

	//@Query("{'location':{'$near' : [?0,?1]}}")
	@Query("{ location:{$nearSphere :?0}}")
	List<Shop> sortByDistance(Point a ); 
}
