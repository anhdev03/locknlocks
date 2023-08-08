package com.locknlock.locknlocks.locknlocks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.locknlock.locknlocks.locknlocks.model.Image;
import com.locknlock.locknlocks.locknlocks.service.ImageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/image/")
public class ImageController {
	@Autowired
	private ImageService imageService;
	
	@GetMapping("{productId}")
	public List<Image> getByProductId(@PathVariable(value = "productId")Integer productId) {
		return imageService.getByProductId(productId);
	}
	
	@PostMapping("add")
	public boolean addImage(@RequestBody Image image) {
		return imageService.addImage(image);
	}
	
	@PostMapping("add-multiple")
	public boolean addMultipleImage(@RequestBody List<Image> images) {
		for (Image image : images) {
			imageService.addImage(image);
        }
		return true;
	}
	
	@DeleteMapping("{id}")
	public boolean deleteImage(@PathVariable(value = "id") Long id) {
		Image image = imageService.getImageById(id);
		if(image != null) {
			return imageService.deleteImage(image);
		}else {
			return false;
		}
	}
}
