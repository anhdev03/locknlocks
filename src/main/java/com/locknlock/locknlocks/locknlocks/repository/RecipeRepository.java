package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locknlock.locknlocks.locknlocks.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{
	@Query(value = "SELECT * "
			+ "FROM recipe "
			+ "WHERE status = true "
			+ "AND trash = false "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Recipe> findAllRecipe();
	
	@Query(value = "SELECT * "
			+ "FROM recipe "
			+ "WHERE status = true "
			+ "AND trash = false "
			+ "ORDER BY id DESC "
			+ "LIMIT ?1",
			nativeQuery = true)
	List<Recipe> findAllRecipe(Integer limit);
	
	@Query(value = "SELECT * "
			+ "FROM recipe "
			+ "WHERE trash = false "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Recipe> findListRecipe();
	
	@Query(value = "SELECT * "
			+ "FROM recipe "
			+ "WHERE trash = true "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Recipe> findTrashRecipe();
	
	Recipe findRecipeById(Long id);
}
