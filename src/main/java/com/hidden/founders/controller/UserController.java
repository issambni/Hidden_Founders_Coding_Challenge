package com.hidden.founders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hidden.founders.component.DislikesAsyncComponent;
import com.hidden.founders.model.Location;
import com.hidden.founders.model.Shop;
import com.hidden.founders.model.UserAccount;
import com.hidden.founders.service.IShopService;
import com.hidden.founders.service.IUserService;

@RestController
public class UserController {
	@Autowired
	DislikesAsyncComponent dislikesComponent;
	@Autowired
	private IUserService userService;
	@Autowired
	private IShopService shopService;
	
	
	@RequestMapping(value = "/users" , method = RequestMethod.GET)
	public ResponseEntity<List<UserAccount>> list() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	@RequestMapping(value = "/users" , method = RequestMethod.POST)
	public ResponseEntity<UserAccount> create(@RequestBody UserAccount user) {
		return ResponseEntity.ok(userService.createUser(user));
	}
	
	@RequestMapping(value = "/users/{id}" , method = RequestMethod.GET)
	public ResponseEntity<UserAccount> showShop(@PathVariable String id) {
		return ResponseEntity.ok(userService.getOneById(id));
	}
	
	
	@RequestMapping(value = "/users/{id}/shops/likes" , method = RequestMethod.POST)
	public ResponseEntity<Shop> likeShop(@PathVariable String id, @RequestBody Shop shop) {
		return ResponseEntity.ok(shopService.likeShop(shop.getId(), id));
	}
	
	@RequestMapping(value = "/users/{id}/shops/unlike" , method = RequestMethod.POST)
	public ResponseEntity<Shop> unlikeShop(@PathVariable String id, @RequestBody Shop shop) {
		return ResponseEntity.ok(shopService.unlikeShop(shop.getId(),id));
	}
	
	@RequestMapping(value = "/users/{id}/shops/likes" , method = RequestMethod.GET)
	public ResponseEntity<List<Shop>> getLikedShops(@PathVariable String id) {
		return ResponseEntity.ok(shopService.getPreferredShopsByUserId(id));
	}
	
	@RequestMapping(value = "/users/{id}/shops/nearBy" , method = RequestMethod.POST)
	public ResponseEntity<List<Shop>> getNearByShops(@PathVariable String id, @RequestBody Location location) {
		dislikesComponent.updateDislikes(id);
		return ResponseEntity.ok(shopService.findNearByShopsByUserId(new Point(Double.valueOf(location.getCoordinates().get(0)),Double.valueOf(location.getCoordinates().get(1))), id));
	}
	
	@RequestMapping(value = "/users/{id}/shops/dislikes" , method = RequestMethod.POST)
	public ResponseEntity<Shop> dislikeShop(@PathVariable String id, @RequestBody Shop shop) {
		return ResponseEntity.ok(shopService.dislikeShop(shop.getId(), id));
	}
	
}
	

	
	
