package com.locknlock.locknlocks.locknlocks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locknlock.locknlocks.locknlocks.dto.OrderDetails;
import com.locknlock.locknlocks.locknlocks.model.OrderDetail;
import com.locknlock.locknlocks.locknlocks.service.OrderDetailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orderdetail/")
public class OrderDetailController {
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@GetMapping("{orderId}")
	public List<OrderDetails> getOrderDetailById(@PathVariable(value = "orderId") Long orderid) {
		return orderDetailService.getOrderDetailById(orderid);
	}
	
	@PostMapping("add")
	public boolean addOrderDetail(@RequestBody OrderDetail orderdetail) {
		return orderDetailService.addOrderDetail(orderdetail);
	}
}
