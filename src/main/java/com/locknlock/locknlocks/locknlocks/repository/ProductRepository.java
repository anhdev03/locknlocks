package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locknlock.locknlocks.locknlocks.dto.BestSellerProducts;
import com.locknlock.locknlocks.locknlocks.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(value = "select * "
			+ "from product "
			+ "where status = true "
			+ "and trash = false "
			+ "ORDER BY id DESC "
			+ "LIMIT ?1", nativeQuery = true)
	List<Product> findLatestActiveProducts(Integer limit);
	
	@Query(value = "select * "
			+ "from product "
			+ "where status = true "
			+ "and trash = false "
			+ "and banner_id != 0", 
			nativeQuery = true)
	List<Product> findProductByBannerId();
	
	@Query(value = "select * "
			+ "from product "
			+ "where status = true "
			+ "and trash = false "
			+ "ORDER BY sale DESC "
			+ "LIMIT ?1",
			nativeQuery = true)
	List<Product> findDealHotProducts(Integer limit);
	
	@Query(value = "SELECT "
			+ "p.id, "
			+ "p.product_name as productName, "
			+ "p.category_id as categoryId, "
			+ "p.banner_id as bannerId, "
			+ "p.image,"
			+ "p.import_price as importPrice, "
			+ "p.price, "
			+ "p.sale, "
			+ "p.quantity, "
			+ "p.detail_image as detailImage, "
			+ "p.details, "
			+ "p.favorite_count as favoriteCount, "
			+ "p.status, "
			+ "p.trash, "
			+ "SUM(od.quantity) AS totalSold "
			+ "FROM product p "
			+ "JOIN order_details od ON p.id = od.product_id "
			+ "GROUP BY p.id, p.product_name, p.price "
			+ "ORDER BY totalSold DESC "
			+ "LIMIT ?1",
			nativeQuery = true)
	List<BestSellerProducts> findBestSellerProducts(Integer limit);
	
	@Query(value = "SELECT p.* "
			+ "FROM product p "
			+ "JOIN product_favorite pf ON p.id = pf.product_id "
			+ "WHERE pf.user_id = ?1",
			nativeQuery = true)
	List<Product> findFavoriteProduct(Integer userId);
	
	@Query(value = "select * "
			+ "from product "
			+ "where status = true "
			+ "and trash = false "
			+ "and category_id = ?1 "
			+ "ORDER BY created_at DESC", 
			nativeQuery = true)
	List<Product> findByCategoryId(Long categoryId);
	
	@Query(value = "select "
			+ "p.id, "
			+ "p.product_name, "
			+ "p.category_id, "
			+ "p.banner_id, "
			+ "p.image, "
			+ "p.import_price, "
			+ "p.price, "
			+ "p.sale, "
			+ "p.quantity, "
			+ "p.favorite_count, "
			+ "p.detail_image, "
			+ "p.details, "
			+ "p.status, "
			+ "p.trash, "
			+ "p.created_at "
			+ "from product p "
			+ "inner join category c "
			+ "on p.category_id = c.id "
			+ "where p.status = true and p.trash = false and c.parent = ?1",
			nativeQuery = true)
	List<Product> findProductByParentCategoryId(Long id);
	
	@Query(value = "SELECT * FROM product WHERE LOWER(unaccent(product_name)) LIKE LOWER(unaccent(:name))", nativeQuery = true)
	List<Product> searchProductByName(@Param("name") String name);

    Product findProductById(Long id);
			
	@Query(value = "SELECT * "
			+ "FROM product "
			+ "WHERE trash = false "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Product> findListProduct();
	
	@Query(value = "SELECT * "
			+ "FROM product "
			+ "WHERE trash = true "
			+ "ORDER BY id DESC",
			nativeQuery = true)
	List<Product> findTrashProduct();
}
