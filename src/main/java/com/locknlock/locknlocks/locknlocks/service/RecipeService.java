package com.locknlock.locknlocks.locknlocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locknlock.locknlocks.locknlocks.model.Recipe;
import com.locknlock.locknlocks.locknlocks.repository.RecipeRepository;

@Service
public class RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;
	
	public List<Recipe> getAllRecipe() {
		return recipeRepository.findAllRecipe();
	}
	
	public List<Recipe> getAllRecipe(Integer limit) {
		return recipeRepository.findAllRecipe(limit);
	}
	
	public Recipe getRecipeById(Long id) {
		return recipeRepository.findRecipeById(id);
	}
	
	public List<Recipe> getListRecipe() {
		return recipeRepository.findListRecipe();
	}
	
	public List<Recipe> getTrashRecipe() {
		return recipeRepository.findTrashRecipe();
	}
	
    public boolean addRecipe(Recipe recipe) {
    	try {
    		recipeRepository.save(recipe);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteRecipe(Recipe recipe) {
    	try {
    		recipeRepository.delete(recipe);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
