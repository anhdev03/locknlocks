package com.locknlock.locknlocks.locknlocks.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locknlock.locknlocks.locknlocks.model.Address;
import com.locknlock.locknlocks.locknlocks.service.AddressService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/address/")
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@GetMapping("{id}")
	public Address getAddressById(@PathVariable(value = "id")Long id) {
		return addressService.getAddressById(id);
	}
	
	@GetMapping("user/{userId}")
	public List<Address> getAddressByUser(@PathVariable(value = "userId")Integer userId) {
		return addressService.getAddressByUser(userId);
	}
	
	@PostMapping("add")
	public boolean addtAddress(@RequestBody Address address) {
		address.setUpdatedAt(LocalDateTime.now());
		return addressService.addAddress(address);
	}
	
	@Transactional
	@PutMapping("{id}")
	public boolean updateAddress(@PathVariable(value = "id") Long id,
			@Validated @RequestBody Address addressDetail) {
		Address address = addressService.getAddressById(id);
		if (address != null) {
			address.setUserId(addressDetail.getUserId());
			address.setPhoneNumber(addressDetail.getPhoneNumber());
			address.setHouseNumber(addressDetail.getHouseNumber());
			address.setStreet(addressDetail.getStreet());
			address.setWard(addressDetail.getWard());
			address.setDistrict(addressDetail.getDistrict());
			address.setCity(addressDetail.getCity());
			address.setUpdatedAt(LocalDateTime.now());
			return addressService.addAddress(address);
		} else {
			return false;
		}
	}
	
	@DeleteMapping("{id}")
	public boolean deleteProduct(@PathVariable(value = "id") Long id) {
		Address address = addressService.getAddressById(id);
		if (address != null) {
			return addressService.deleteAddress(address);
		} else {
			return false;
		}
	}
}
