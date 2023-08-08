package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locknlock.locknlocks.locknlocks.model.Guide;

public interface GuideRepository extends JpaRepository<Guide, Long>{
	@Query(value = "SELECT * "
			+ "FROM guide "
			+ "WHERE status = true "
			+ "AND trash = false "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Guide> findAllGuide();
	
	@Query(value = "SELECT * "
			+ "FROM guide "
			+ "WHERE trash = false "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Guide> findListGuide();
	
	@Query(value = "SELECT * "
			+ "FROM guide "
			+ "WHERE trash = true "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Guide> findTrashGuide();
	
	Guide findGuideById(Long id);
}
