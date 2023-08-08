package com.locknlock.locknlocks.locknlocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locknlock.locknlocks.locknlocks.dto.OrderDetails;
import com.locknlock.locknlocks.locknlocks.model.OrderDetail;

public interface OrderdetailRepository extends JpaRepository<OrderDetail, Long>{
	@Query(value = "SELECT "
			+ "od.order_id as orderId, "
			+ "p.product_name as productName, "
			+ "od.quantity, "
			+ "p.image, "
			+ "p.id, "
			+ "p.price, "
			+ "p.sale "
			+ "FROM order_details od "
			+ "inner join product  p "
			+ "ON od.product_id = p.id "
			+ "WHERE od.order_id =:orderid", nativeQuery = true)
	List<OrderDetails> findOrderDetailById(@Param("orderid") Long orderid);
}
