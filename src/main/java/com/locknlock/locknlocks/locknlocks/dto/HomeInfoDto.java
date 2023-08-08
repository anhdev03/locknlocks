package com.locknlock.locknlocks.locknlocks.dto;

import org.springframework.beans.factory.annotation.Value;

public interface HomeInfoDto {
	String getProductName();
	String getImage();
	long getPrice();
	
	@Value("#{target.banner}")
	String getBanner();
}
