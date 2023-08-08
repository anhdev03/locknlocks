package com.locknlock.locknlocks.locknlocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locknlock.locknlocks.locknlocks.model.Banner;
import com.locknlock.locknlocks.locknlocks.repository.BannerRepository;

@Service
public class BannerService {
	@Autowired
	private BannerRepository bannerRepository;
	
	public List<Banner> getActiveBanners() {
		return bannerRepository.findActiveBanners();
	}
	
	public Banner getBannerById(Long id) {
    	return bannerRepository.findBannerById(id);
    }
    
    public List<Banner> getListBanner() {
    	return bannerRepository.findListBanner();
    }
    
    public List<Banner> getTrashBanner() {
    	return bannerRepository.findTrashBanner();
    }
    
    public boolean addBanner(Banner banner) {
    	try {
    		bannerRepository.save(banner);
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean deleteBanner(Banner banner) {
    	try {
    		bannerRepository.delete(banner);
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
}
