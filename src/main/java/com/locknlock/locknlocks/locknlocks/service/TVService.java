package com.locknlock.locknlocks.locknlocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locknlock.locknlocks.locknlocks.model.TV;
import com.locknlock.locknlocks.locknlocks.repository.TVRepository;
@Service
public class TVService {
	@Autowired
	private TVRepository tvRepository;
	
	public List<TV> getAllTV() {
		return tvRepository.findAllTV();
	}
	
	public List<TV> getAllTV(Integer limit) {
		return tvRepository.findAllTV(limit);
	}
	
	public TV getTVById(Long id) {
		return tvRepository.findTVById(id);
	}
	
	public List<TV> getListTV() {
		return tvRepository.findListTV();
	}
	
	public List<TV> getTrashTV() {
		return tvRepository.findTrashTV();
	}
	
    public boolean addTV(TV tv) {
    	try {
    		tvRepository.save(tv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteTV(TV tv) {
    	try {
    		tvRepository.delete(tv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
