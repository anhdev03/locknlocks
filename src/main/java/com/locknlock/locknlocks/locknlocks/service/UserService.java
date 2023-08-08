package com.locknlock.locknlocks.locknlocks.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.locknlock.locknlocks.locknlocks.model.User;
import com.locknlock.locknlocks.locknlocks.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
    public User getUserById(Long id) {
    	return userRepository.findUserById(id);
    }
    
    public List<User> getByRoles_Name(){
    	return userRepository.findByRoles_Name();
    }
    
    public User getUserByEmail(String email) {
    	return userRepository.findUserByEmail(email);
    }

    public List<User> getListUser() {
    	return userRepository.findListUser();
    }
    
    public List<User> getListUserByDate(LocalDate startDate, LocalDate endDate) {
    	return userRepository.findListUserByDate(startDate, endDate);
    }
    
    public List<User> getTrashUser() {
    	return userRepository.findTrashUser();
    }
    
    public boolean addUser(User user) {
    	try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteUser(User user) {
    	try {
            userRepository.delete(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
