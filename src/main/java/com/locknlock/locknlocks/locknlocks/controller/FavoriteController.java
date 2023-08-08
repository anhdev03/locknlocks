package com.locknlock.locknlocks.locknlocks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locknlock.locknlocks.locknlocks.model.Favorite;
import com.locknlock.locknlocks.locknlocks.service.FavoriteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/favorite/")
public class FavoriteController {
	@Autowired
	private FavoriteService favoriteService;
	
	@PostMapping("add")
	public Favorite addFavorite(@RequestBody Favorite favorite) {
	    return favoriteService.addFavorite(favorite);
	}
	
	@GetMapping("user/{userId}")
	public List<Favorite> getFavoriteByUserId(@PathVariable(value = "userId")Integer userId) {
		return favoriteService.getFavoriteByUserId(userId);
	}

	
	@DeleteMapping("{userId}/{productId}")
	public boolean deleteFavorite(
	    @PathVariable(value = "userId") Integer userId,
	    @PathVariable(value = "productId") Integer productId
	) {
	    Favorite favorite = favoriteService.getFavoriteByUserIdAndProductId(userId, productId);
	    if (favorite != null) {
	        return favoriteService.deleteFavorite(favorite);
	    } else {
	        return false;
	    }
	}
}
