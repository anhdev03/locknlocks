package com.locknlock.locknlocks.locknlocks.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locknlock.locknlocks.locknlocks.model.Order;
import com.locknlock.locknlocks.locknlocks.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
    public Order getOrderById(Long id) {
    	return orderRepository.findOrderById(id);
    }
    
    public List<Order> getOrderByUser(String username) {
    	return orderRepository.findOrderByUser(username);
    }
    
    public List<Order> getOrderByUserTrash(String username) {
    	return orderRepository.findOrderByUserTrash(username);
    }
    
    public List<Order> getOrderByStatus(String status) {
    	return orderRepository.findOrderByStatus(status);
    }
	
	public Long getIdOrderLast() {
		return orderRepository.findIdOrderLast();
	}
	
	public List<Order> getListOrder() {
		return orderRepository.findListOrder();
	}
	
	public List<Order> getListOrderByDate(LocalDate startDate, LocalDate endDate) {
		return orderRepository.findListOrderByDate(startDate, endDate);
	}
	
	public List<Order> getAllOrder(LocalDate startDate, LocalDate endDate) {
		return orderRepository.findAllOrder(startDate, endDate);
	}
	
	public List<Order> getConfirmOrder() {
		return orderRepository.findConfirmOrder();
	}
	
	public List<Order> getTrashOrder() {
		return orderRepository.findTrashOrder();
	}
	
    public boolean addOrder(Order order) {
    	try {
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteOrder(Order order) {
    	try {
            orderRepository.delete(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
