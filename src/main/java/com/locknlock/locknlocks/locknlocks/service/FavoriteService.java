package com.locknlock.locknlocks.locknlocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locknlock.locknlocks.locknlocks.model.Favorite;
import com.locknlock.locknlocks.locknlocks.repository.FavoriteRepository;

@Service
public class FavoriteService {
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	public Favorite getFavoriteById(Long id) {
		return favoriteRepository.findFavoriteById(id);
	}
	
	public List<Favorite> getFavoriteByUserId(Integer userId) {
		return favoriteRepository.findFavoriteByUserId(userId);
	}
	
	public Favorite getFavoriteByUserIdAndProductId(Integer userId, Integer productId) {
	    return favoriteRepository.findByUserIdAndProductId(userId, productId);
	}
	
	public Favorite addFavorite(Favorite favorite) {
	    try {
	        return favoriteRepository.save(favorite);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public boolean deleteFavorite(Favorite favorite) {
		try {
			favoriteRepository.delete(favorite);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
