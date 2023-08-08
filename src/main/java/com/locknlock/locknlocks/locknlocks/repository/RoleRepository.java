package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locknlock.locknlocks.locknlocks.model.ERole;
import com.locknlock.locknlocks.locknlocks.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
	@Query(value = "SELECT * FROM roles WHERE status = true and trash = false", nativeQuery = true)
	List<Role> getListRole();
	
	@Query(value = "SELECT * FROM roles WHERE trash = true", nativeQuery = true)
	List<Role> getTrashRole();
}