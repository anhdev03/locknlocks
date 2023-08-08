package com.locknlock.locknlocks.locknlocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locknlock.locknlocks.locknlocks.model.Category;
import com.locknlock.locknlocks.locknlocks.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> getActiveCategories() {
        return categoryRepository.findActiveCategories();
    }
    public List<Category> getActiveCategoriesByParent(Integer parent) {
    	return categoryRepository.findActiveCategoriesByParent(parent);
    }
    public List<Category> getActiveCategoriesByParentR(Integer parent) {
    	return categoryRepository.findActiveCategoriesByParentR(parent);
    }
    public List<Category> getActiveImageCategories() {
    	return categoryRepository.findActiveImageCategories();
    }
    
    public Category getCategoryById(Long id) {
    	return categoryRepository.findCategoryById(id);
    }
    
    public List<Category> getListCategory() {
    	return categoryRepository.findListCategory();
    }
    public List<Category> getTrashCategory() {
    	return categoryRepository.findTrashCategory();
    }  
    
    public boolean addCategory(Category category) {
    	try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteCategory(Category category) {
    	try {
            categoryRepository.delete(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
