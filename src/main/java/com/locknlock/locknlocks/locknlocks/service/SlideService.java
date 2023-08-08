package com.locknlock.locknlocks.locknlocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.locknlock.locknlocks.locknlocks.model.Slide;
import com.locknlock.locknlocks.locknlocks.repository.SlideRepository;


@Service
public class SlideService {
	@Autowired
    private SlideRepository slideRepository;
    
    public List<Slide> getLastActiveSlide() {
        return slideRepository.findLastActiveSlide();
    }
    
    public Slide getSlideById(Long id) {
    	return slideRepository.findSlideById(id);
    }
    
    public List<Slide> getListSlide() {
    	return slideRepository.findListSlide();
    }
    
    public List<Slide> getTrashSlide() {
    	return slideRepository.findTrashSlide();
    }
    
    public boolean addSlide(Slide slide) {
    	try {
    		slideRepository.save(slide);
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean deleteSlide(Slide slide) {
    	try {
    		slideRepository.delete(slide);
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
}
