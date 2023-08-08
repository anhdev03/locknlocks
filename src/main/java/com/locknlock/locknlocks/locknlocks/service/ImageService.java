package com.locknlock.locknlocks.locknlocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.locknlock.locknlocks.locknlocks.model.Image;
import com.locknlock.locknlocks.locknlocks.repository.ImageRepository;

@Service
public class ImageService {
	@Autowired
	private ImageRepository imageRepository;
	
	public Image getImageById(Long id) {
		return imageRepository.findImageById(id);
	}
	
	public List<Image> getByProductId(Integer productId) {
		return imageRepository.findByProductId(productId);
	}
	
    public boolean addImage(Image image) {
    	try {
    		imageRepository.save(image);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteImage(Image image) {
    	try {
    		imageRepository.delete(image);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
