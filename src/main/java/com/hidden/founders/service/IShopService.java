package com.hidden.founders.service;

import java.util.List;

import org.springframework.data.geo.Point;

import com.hidden.founders.model.Shop;
import com.hidden.founders.model.UserDislike;

public interface IShopService {
	
	List<Shop> findAll();
	
	Shop getOneById(String idShop);
	
	List<String> getPreferredByUserIds(String idShop);
	
	List<UserDislike> getDislikedByUserIds(String idShop);

	Shop dislikeShop(String idShop, String idUser);

	Shop likeShop(String idShop, String idUser);
	
	Shop unlikeShop(String idShop, String idUser);

	List<Shop> getPreferredShopsByUserId(String idUser);

	List<Shop> findNearByShopsByUserId(Point point, String idUser);
	
	List<Shop> findInValidDislikedShopByUserIds(String idUser);
	
	List<Shop> removeInValidDislikedShopsByUserIds(String idUser);

}
