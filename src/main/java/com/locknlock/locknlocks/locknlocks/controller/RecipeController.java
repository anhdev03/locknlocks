package com.locknlock.locknlocks.locknlocks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locknlock.locknlocks.locknlocks.model.Recipe;
import com.locknlock.locknlocks.locknlocks.service.RecipeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/recipe/")
public class RecipeController {
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping("all")
	public List<Recipe> getAllRecipe() {
		return recipeService.getAllRecipe();
	}
	
	@GetMapping("limit/{limit}")
	public List<Recipe> getAllRecipe(@PathVariable(value = "limit")Integer limit) {
		return recipeService.getAllRecipe(limit);
	}
	
	@GetMapping("{id}")
	public Recipe getRecipeById(@PathVariable(value = "id") Long id) {
		return recipeService.getRecipeById(id);
	}
	
	@GetMapping("list")
	public List<Recipe> getListRecipe() {
		return this.recipeService.getListRecipe();
	}

	@GetMapping("trash")
	public List<Recipe> getTrashRecipe() {
		return this.recipeService.getTrashRecipe();
	}
	
	@PostMapping("add")
	public boolean addRecipe(@RequestBody Recipe recipe) {
		return recipeService.addRecipe(recipe);
	}
	
	@PutMapping("{id}")
	public boolean updateRecipe(@PathVariable(value = "id") Long recipeId,
			@Validated @RequestBody Recipe recipeDetail) {
		Recipe recipe = recipeService.getRecipeById(recipeId);
		if (recipe != null) {
			recipe.setDetails(recipeDetail.getDetails());
			recipe.setImage(recipeDetail.getImage());
			recipe.setLink(recipeDetail.getLink());
			recipe.setName(recipeDetail.getName());
			recipe.setShortDetail(recipeDetail.getShortDetail());
			recipe.setStatus(recipeDetail.getStatus());
			recipe.setTrash(recipeDetail.getTrash());
			return recipeService.addRecipe(recipe);
		} else {
			return false;
		}
	}
	
	@PatchMapping("{id}")
	public boolean updateRecipeStatusAndTrash(@PathVariable(value = "id") Long recipeId,
			@Validated @RequestBody Recipe recipeDetail) {
		Recipe recipe = recipeService.getRecipeById(recipeId);
		if (recipe != null) {
			recipe.setStatus(recipeDetail.getStatus());
			recipe.setTrash(recipeDetail.getTrash());
			return recipeService.addRecipe(recipe);
		} else {
			return false;
		}
	}
	
	@DeleteMapping("{id}")
	public boolean deleteRecipe(@PathVariable(value = "id") Long recipeId) {
		Recipe recipe = recipeService.getRecipeById(recipeId);
		if (recipe != null) {
			return recipeService.deleteRecipe(recipe);
		} else {
			return false;
		}
	}
}
