package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locknlock.locknlocks.locknlocks.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	@Query(value="SELECT "
			+ "* "
			+ "FROM category "
			+ "WHERE status = true AND trash = false "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Category> findActiveCategories();
	
	@Query(value = "SELECT "
			+ "* "
			+ "FROM category "
			+ "WHERE parent = ?1 AND status = true AND trash = false "
			+ "ORDER BY id ASC", 
			nativeQuery = true)
	List<Category> findActiveCategoriesByParent(Integer parent);
	
	@Query(value = "SELECT "
			+ "* "
			+ "FROM category "
			+ "WHERE parent = ?1 AND status = true AND trash = false "
			+ "ORDER BY id ASC "
			+ "LIMIT 5", 
			nativeQuery = true)
	List<Category> findActiveCategoriesByParentR(Integer parent);
	
	@Query(value = "SELECT "
			+ "* "
			+ "FROM category "
			+ "WHERE image IS NOT NULL AND status = true AND trash = false "
			+ "ORDER BY created_at DESC LIMIT 8", nativeQuery = true)
	List<Category> findActiveImageCategories();
	
	Category findCategoryById(long id);

	@Query(value="SELECT "
			+ "* "
			+ "FROM category "
			+ "WHERE trash = false "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Category> findListCategory();
	
	
	@Query(value="SELECT "
			+ "* "
			+ "FROM category "
			+ "WHERE trash = true "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Category> findTrashCategory();
}
