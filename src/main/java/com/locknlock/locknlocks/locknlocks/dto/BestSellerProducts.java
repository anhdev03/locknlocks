package com.locknlock.locknlocks.locknlocks.dto;

import java.math.BigDecimal;

public interface BestSellerProducts{
	long getId();
	String getProductName();
	long getcategoryId();
	Integer getBannerId();
	String getImage();
	BigDecimal getImportPrice();
	BigDecimal getPrice();
	long getSale();
	Integer getQuantity();
	String getDetailImage();
	String getDetails();
	long getFavoriteCount();
	Boolean getStatus();
	Boolean getTrash();
	long getTotalSold();
}
