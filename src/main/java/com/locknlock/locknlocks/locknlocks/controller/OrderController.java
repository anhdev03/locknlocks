package com.locknlock.locknlocks.locknlocks.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.locknlock.locknlocks.locknlocks.model.Order;
import com.locknlock.locknlocks.locknlocks.repository.OrderRepository;
import com.locknlock.locknlocks.locknlocks.service.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order/")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("lastid")
	public Long getIdOrderLast() {
		return orderService.getIdOrderLast();
	}
	
	@GetMapping("{id}")
	public Order getOrderById(@PathVariable(value = "id") Long orderId) {
		return orderService.getOrderById(orderId);
	}
	
	@GetMapping("user/{username}")
	public List<Order> getOrderByUser(@PathVariable(value = "username") String username) {
		return orderService.getOrderByUser(username);
	}
	
	@GetMapping("usertrash/{username}")
	public List<Order> getOrderByUserTrash(@PathVariable(value = "username") String username) {
		return orderService.getOrderByUserTrash(username);
	}
	
	@GetMapping("status/{status}")
	public List<Order> getOrderByStatus(@PathVariable(value = "status") String status) {
		return orderService.getOrderByStatus(status);
	}
	
	@GetMapping("list")
	public List<Order> getListOrder() {
		return orderService.getListOrder();
	}
	
	@GetMapping("date")
	public List<Order> getListOrderByDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		return orderService.getListOrderByDate(startDate, endDate);
	}
	
	@GetMapping("all")
	public List<Order> getAllOrder(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		return orderService.getAllOrder(startDate, endDate);
	}
	
	@GetMapping("confirm")
	public List<Order> getConfirmOrder() {
		return orderService.getConfirmOrder();
	}
	
	@GetMapping("trash")
	public List<Order> getTrashOrder() {
		return orderService.getTrashOrder();
	}
	
	@PostMapping("add")
	public Order addOrder(@RequestBody Order order) {
		return orderRepository.save(order);
	}
	
	@PutMapping("{id}")
	public boolean updateOrder(@PathVariable(value = "id") Long orderId, @Validated @RequestBody Order orderDetail) {
		Order order = orderService.getOrderById(orderId);
		if(order != null) {
			order.setUsername(orderDetail.getUsername());
			order.setPhone(orderDetail.getPhone());
			order.setEmail(orderDetail.getEmail());
			order.setAddress(orderDetail.getAddress());
			order.setTotal(orderDetail.getTotal());
			order.setConfirm(orderDetail.getConfirm());
			order.setStatus(orderDetail.getStatus());
			order.setTrash(orderDetail.getTrash());
			return orderService.addOrder(order);
		}else {
			return false;
		}
	}
	
	@PatchMapping("{id}")
	public boolean updateCategoryConfirmAndTrash(@PathVariable(value = "id") Long orderId, @Validated @RequestBody Order orderDetail) {
		Order order = orderService.getOrderById(orderId);
		if(order != null) {
			order.setConfirm(orderDetail.getConfirm());
			order.setTrash(orderDetail.getTrash());
			return orderService.addOrder(order);
		}else {
			return false;
		}
	}
	
	@PatchMapping("status/{id}")
	public boolean updateCategoryStatus(@PathVariable(value = "id") Long orderId, @Validated @RequestBody Order orderDetail) {
		Order order = orderService.getOrderById(orderId);
		if(order != null) {
			order.setStatus(orderDetail.getStatus());
			return orderService.addOrder(order);
		}else {
			return false;
		}
	}
	
	
	@DeleteMapping("{id}")
    public boolean deleteOrder(@PathVariable(value = "id") Long orderId) {
		Order order = orderService.getOrderById(orderId);
		if(order != null) {
			return orderService.deleteOrder(order);
		}else {
			return false;
		}
    }
	
}
