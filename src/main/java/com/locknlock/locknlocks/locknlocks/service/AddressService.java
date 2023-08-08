package com.locknlock.locknlocks.locknlocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locknlock.locknlocks.locknlocks.model.Address;
import com.locknlock.locknlocks.locknlocks.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;
	
	public Address getAddressById(Long id) {
		return addressRepository.findAddressById(id);
	}
	
	public List<Address> getAddressByUser(Integer userId) {
		return addressRepository.findAddressByUser(userId);
	}
	
	public boolean addAddress(Address address) {
		try {
			addressRepository.save(address);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteAddress(Address address){
		try {
			addressRepository.delete(address);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
