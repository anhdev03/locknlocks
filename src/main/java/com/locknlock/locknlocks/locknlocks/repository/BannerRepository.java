package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.locknlock.locknlocks.locknlocks.model.Banner;


public interface BannerRepository extends JpaRepository<Banner, Long>{
	
	@Query(value="select "
			+ "* "
			+ "from banner "
			+ "where status = true "
			+ "and trash = false "
			+ "ORDER BY id DESC ",
			nativeQuery = true)
	List<Banner> findActiveBanners();
	
	Banner findBannerById(long id);

	@Query(value="SELECT "
			+ "* "
			+ "FROM banner "
			+ "WHERE trash = false "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Banner> findListBanner();
	
	
	@Query(value="SELECT "
			+ "* "
			+ "FROM banner "
			+ "WHERE trash = true "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Banner> findTrashBanner();
}
