package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.locknlock.locknlocks.locknlocks.model.Slide;

public interface SlideRepository extends JpaRepository<Slide, Long>{
	@Query(value = "select * "
			+ "from slide "
			+ "where status = true "
			+ "and trash = false "
			+ "ORDER BY id DESC "
			+ "LIMIT 5", nativeQuery = true)
	List<Slide> findLastActiveSlide();
	
	Slide findSlideById(long id);
	
	@Query(value="SELECT "
			+ "* "
			+ "FROM slide "
			+ "WHERE trash = false "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Slide> findListSlide();
	
	@Query(value="SELECT "
			+ "* "
			+ "FROM slide "
			+ "WHERE trash = true "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Slide> findTrashSlide();
}
