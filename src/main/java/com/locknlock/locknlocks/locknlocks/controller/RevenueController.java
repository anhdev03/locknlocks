package com.locknlock.locknlocks.locknlocks.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.locknlock.locknlocks.locknlocks.model.Revenue;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/revenue")
public class RevenueController {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@GetMapping
	public Revenue getRevenue(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
	                          @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
	    String sql = "SELECT SUM((p.price - p.import_price) * od.Quantity) AS TotalRevenue " +
	            "FROM orders o " +
	            "JOIN order_details od ON o.id = od.order_id " +
	            "JOIN product p ON od.product_id = p.id " +
	            "WHERE o.status = 'Đã Giao' " +
	            "AND o.order_date >= :startDate " +
	            "AND o.order_date <= :endDate";

	    MapSqlParameterSource parameters = new MapSqlParameterSource()
	            .addValue("startDate", startDate)
	            .addValue("endDate", endDate);

	    Double totalRevenue = namedParameterJdbcTemplate.queryForObject(sql, parameters, Double.class);

	    return new Revenue(totalRevenue != null ? totalRevenue : 0.0);
	}


}
