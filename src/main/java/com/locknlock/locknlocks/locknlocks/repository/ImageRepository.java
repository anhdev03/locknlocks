package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locknlock.locknlocks.locknlocks.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
	Image findImageById(long id);
	@Query(value = "SELECT * "
			+ "FROM product_images "
			+ "WHERE product_id = ?1 "
			+ "ORDER BY id DESC "
			+ "LIMIT 3", nativeQuery = true)
	List<Image> findByProductId(Integer productId);
}
