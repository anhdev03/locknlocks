package com.locknlock.locknlocks.locknlocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locknlock.locknlocks.locknlocks.model.Guide;
import com.locknlock.locknlocks.locknlocks.repository.GuideRepository;

@Service
public class GuideService {
	@Autowired
	private GuideRepository guideRepository;
	
	public List<Guide> getAllGuide() {
		return guideRepository.findAllGuide();
	}
	
	public Guide getGuideById(Long id) {
		return guideRepository.findGuideById(id);
	}
	
	public List<Guide> getListGuide() {
		return guideRepository.findListGuide();
	}
	
	public List<Guide> getTrashGuide() {
		return guideRepository.findTrashGuide();
	}
	
    public boolean addGuide(Guide guide) {
    	try {
    		guideRepository.save(guide);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteGuide(Guide guide) {
    	try {
    		guideRepository.delete(guide);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
