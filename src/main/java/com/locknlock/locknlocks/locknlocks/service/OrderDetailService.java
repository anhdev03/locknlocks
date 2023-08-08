package com.locknlock.locknlocks.locknlocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locknlock.locknlocks.locknlocks.dto.OrderDetails;
import com.locknlock.locknlocks.locknlocks.model.OrderDetail;
import com.locknlock.locknlocks.locknlocks.repository.OrderdetailRepository;

@Service
public class OrderDetailService {
	@Autowired
	private OrderdetailRepository orderdetailRepository;
	
	public List<OrderDetails> getOrderDetailById(Long orderId) {
		return orderdetailRepository.findOrderDetailById(orderId);
	}
	
    public boolean addOrderDetail(OrderDetail orderDetail) {
    	try {
    		orderdetailRepository.save(orderDetail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
