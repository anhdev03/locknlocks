package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locknlock.locknlocks.locknlocks.model.TV;

public interface TVRepository extends JpaRepository<TV, Long>{
	@Query(value = "SELECT * "
			+ "FROM tv "
			+ "WHERE status = true "
			+ "AND trash = false "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<TV> findAllTV();
	
	@Query(value = "SELECT * "
			+ "FROM tv "
			+ "WHERE status = true "
			+ "AND trash = false "
			+ "ORDER BY id DESC "
			+ "LIMIT ?1",
			nativeQuery = true)
	List<TV> findAllTV(Integer limit);
	
	@Query(value = "SELECT * "
			+ "FROM tv "
			+ "WHERE trash = false "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<TV> findListTV();
	
	@Query(value = "SELECT * "
			+ "FROM tv "
			+ "WHERE trash = true "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<TV> findTrashTV();
	
	TV findTVById(Long id);
}
