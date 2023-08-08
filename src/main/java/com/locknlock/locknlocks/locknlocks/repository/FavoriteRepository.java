package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locknlock.locknlocks.locknlocks.model.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
	Favorite findFavoriteById(Long id);
	
	@Query(value = "select * "
			+ "from product_favorite "
			+ "where user_id = ?1 "
			+ "ORDER BY id DESC ", nativeQuery = true)
	List<Favorite> findFavoriteByUserId(Integer userId);
	
	Favorite findByUserIdAndProductId(Integer userId, Integer productId);
	
}
