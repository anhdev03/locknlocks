package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locknlock.locknlocks.locknlocks.dto.HomeInfoDto;
import com.locknlock.locknlocks.locknlocks.model.Product;


public interface HomeRepository extends JpaRepository<Product, Long>{
	@Query(value="select " 
			+ "p.product_name as productName, "
			+ "p.image, "
			+ "p.price, "
			+ "b.banner " 
			+ "from products p " 
			+ "inner join brands b " 
			+ "on p.brand_id = b.id ",
	nativeQuery = true)
	List<HomeInfoDto> getAllProductBrand();
	
	
	
}
