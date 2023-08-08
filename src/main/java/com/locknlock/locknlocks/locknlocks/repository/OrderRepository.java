package com.locknlock.locknlocks.locknlocks.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locknlock.locknlocks.locknlocks.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	Order findOrderById(long id);
	
	@Query(value = "SELECT id "
			+ "FROM orders "
			+ "ORDER BY id DESC "
			+ "LIMIT 1", nativeQuery = true)
	Long findIdOrderLast();
	
	@Query(value = "SELECT * "
			+ "FROM orders "
			+ "WHERE trash = false "
			+ "AND fullname LIKE ?1 "
			+ "ORDER BY id DESC", nativeQuery = true)
	List<Order> findOrderByUser(String username);
	
	@Query(value = "SELECT * "
			+ "FROM orders "
			+ "WHERE trash = true "
			+ "AND fullname LIKE ?1 "
			+ "ORDER BY id DESC", nativeQuery = true)
	List<Order> findOrderByUserTrash(String username);
	
	@Query(value = "SELECT * "
			+ "FROM orders "
			+ "WHERE confirm = true "
			+ "AND trash = false "
			+ "AND status LIKE ?1 "
			+ "ORDER BY id DESC", nativeQuery = true)
	List<Order> findOrderByStatus(String status);
	
	@Query(value = "SELECT * "
			+ "FROM orders "
			+ "WHERE confirm = false "
			+ "AND trash = false "
			+ "ORDER BY id DESC", nativeQuery = true)
	List<Order> findListOrder();
	
	@Query(value = "SELECT * "
			+ "FROM orders "
			+ "WHERE confirm = false "
			+ "AND trash = false "
			+ "AND order_date >= :startDate "
			+ "AND order_date <= :endDate "
			+ "ORDER BY id DESC", nativeQuery = true)
	List<Order> findListOrderByDate(LocalDate startDate, LocalDate endDate);
	
	@Query(value = "SELECT * "
			+ "FROM orders "
			+ "WHERE trash = false "
			+ "AND order_date >= :startDate "
			+ "AND order_date <= :endDate "
			+ "ORDER BY id DESC", nativeQuery = true)
	List<Order> findAllOrder(LocalDate startDate, LocalDate endDate);
	
	@Query(value = "SELECT * "
			+ "FROM orders "
			+ "WHERE confirm = true "
			+ "ORDER BY id DESC", nativeQuery = true)
	List<Order> findConfirmOrder();
	
	@Query(value = "SELECT * "
			+ "FROM orders "
			+ "WHERE trash = true "
			+ "ORDER BY id DESC", nativeQuery = true)
	List<Order> findTrashOrder();
}
