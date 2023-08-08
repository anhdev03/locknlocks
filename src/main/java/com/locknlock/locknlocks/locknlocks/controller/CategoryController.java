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
import com.locknlock.locknlocks.locknlocks.model.Category;
import com.locknlock.locknlocks.locknlocks.service.CategoryService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("all")
    public List<Category> getActiveCategories() {
        return categoryService.getActiveCategories();
    }
    @GetMapping("parent/{parent}")
    public List<Category> getActiveCategoriesByParent(@PathVariable(value = "parent")Integer parent){
    	 return this.categoryService.getActiveCategoriesByParent(parent);
    }
    @GetMapping("parentR/{parent}")
    public List<Category> getActiveCategoriesByParentR(@PathVariable(value = "parent")Integer parent){
    	 return this.categoryService.getActiveCategoriesByParentR(parent);
    }
    @GetMapping("image")
    public List<Category> getActiveImageCategories() {
        return categoryService.getActiveImageCategories();
    }
    
    
	@GetMapping("{id}")
	public Category getCategoryById(@PathVariable(value="id") Long categoryId) {
		return categoryService.getCategoryById(categoryId);
	}
	
	@GetMapping("list")
	public List<Category> getListCategory(){
		return categoryService.getListCategory();
	}
	
	@GetMapping("trash")
	public List<Category> getTrashCategory(){
		return categoryService.getTrashCategory();
	}

	@PostMapping("add")
	public boolean addCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}
	
	@PutMapping("{id}")
	public boolean updateCategory(@PathVariable(value = "id") Long categoryId, @Validated @RequestBody Category categoryDetail) {
		Category category = categoryService.getCategoryById(categoryId);
		if (category != null) {
			category.setCategoryName(categoryDetail.getCategoryName());
			category.setImage(categoryDetail.getImage());
			category.setParent(categoryDetail.getParent());
			category.setStatus(categoryDetail.isStatus());
			category.setTrash(categoryDetail.isTrash());
			return categoryService.addCategory(category); 
	    }else {
	    	return false;
	    }
	}
	
	@PatchMapping("{id}")
	public boolean updateCategoryStatusAndTrash(@PathVariable(value = "id") Long categoryId, @Validated @RequestBody Category categoryDetail) {
		Category category = categoryService.getCategoryById(categoryId);
		if (category != null) {
			category.setStatus(categoryDetail.isStatus());
			category.setTrash(categoryDetail.isTrash());
			return categoryService.addCategory(category);
	    }else {
	    	return false;
	    }
	}
	
	@DeleteMapping("{id}")
    public boolean deleteCategory(@PathVariable(value = "id") Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        if (category != null) {
        	return categoryService.deleteCategory(category);
        }else {
	    	return false;
	    }
    }
}
