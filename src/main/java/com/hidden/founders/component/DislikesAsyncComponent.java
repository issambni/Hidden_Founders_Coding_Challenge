package com.hidden.founders.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.hidden.founders.service.IShopService;

@Component
public class DislikesAsyncComponent {
	/**
	 * I've used an Async component to delete all array entries that holds an invalid dislike,
	 * meaning the dislike surpassed its 2 hour marks.
	 * All the methods of this component will be executed in a different thread, 
	 * for better resources management and availability back-end wise.
	 */
	@Autowired
	IShopService shopService;
	/**
	 * This method invokes another method in the IShopService
	 * @param idUser : id of the user in question.
	 */
	 @Async
	    public void updateDislikes(String idUser) {
		 	shopService.removeInValidDislikedShopsByUserIds(idUser);		 	
	        System.out.println("Execute method asynchronously. " + Thread.currentThread().getName());
	    }

}
