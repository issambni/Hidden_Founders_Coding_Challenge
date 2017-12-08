package com.hidden.founders.service;

import java.util.List;

import com.hidden.founders.model.Shop;
import com.hidden.founders.model.UserDislike;

public interface IShopService {
	
	List<Shop> findAll();
	
	Shop getOneById(String idShop);
	
	List<String> getprefferedByUserIds(String idShop);
	
	List<UserDislike> getdislikedByUserIds(String idShop);

	Shop dislikeShop(String idShop, String idUser);

	Shop likeShop(String idShop, String idUser);

}
