package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locknlock.locknlocks.locknlocks.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	Address findAddressById(Long id);
	@Query(value = "SELECT * "
			+ "FROM user_address "
			+ "WHERE user_id = ?1 "
			+ "ORDER BY updated_at DESC "
			+ "LIMIT 3",
			nativeQuery = true)
	List<Address> findAddressByUser(Integer userId);
}
