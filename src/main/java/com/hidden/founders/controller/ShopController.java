package com.hidden.founders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hidden.founders.model.Shop;
import com.hidden.founders.model.UserDislike;
import com.hidden.founders.service.IShopService;

@RestController
public class ShopController {
	@Autowired
	private IShopService shopService;
	
	
	@RequestMapping(value = "/shops" , method = RequestMethod.GET)
	public ResponseEntity<List<Shop>> list() {
		return ResponseEntity.ok(shopService.findAll());
	}
			
	@RequestMapping(value = "/shops/{id}" , method = RequestMethod.GET)
	public ResponseEntity<Shop> showShop(@PathVariable String id) {
		return ResponseEntity.ok(shopService.getOneById(id));
	}
	
	@RequestMapping(value = "/shops/{id}/likes" , method = RequestMethod.GET)
	public ResponseEntity<List<String>> getPrefferedByUserIds(@PathVariable String id) {
		return ResponseEntity.ok(shopService.getPreferredByUserIds(id));
	}
	
	@RequestMapping(value = "/shops/{id}/dislikes" , method = RequestMethod.GET)
	public ResponseEntity<List<UserDislike>> getDislikedByUserIds(@PathVariable String id) {
		return ResponseEntity.ok(shopService.getDislikedByUserIds(id));
	}
	

	
	

}
