package com.locknlock.locknlocks.locknlocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locknlock.locknlocks.locknlocks.dto.BestSellerProducts;
import com.locknlock.locknlocks.locknlocks.model.Product;
import com.locknlock.locknlocks.locknlocks.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
    private ProductRepository productRepository;
	public List<Product> getLatestActiveProducts(Integer limit) {
    	return productRepository.findLatestActiveProducts(limit);
    }
	
	public List<Product> getProductByBannerId() {
    	return productRepository.findProductByBannerId();
    }
	
	public List<Product> getDealHotProducts(Integer limit) {
    	return productRepository.findDealHotProducts(limit);
    }
	
	public List<BestSellerProducts> getBestSellerProducts(Integer limit) {
    	return productRepository.findBestSellerProducts(limit);
    }
	
	public List<Product> getByCategoryId(Long categoryId) {
    	return productRepository.findByCategoryId(categoryId);
    }
	
	public List<Product> getProductByParentCategoryId(Long parentId) {
		return productRepository.findProductByParentCategoryId(parentId);
	}
	
	public List<Product> getProductByName(String name) {
		return productRepository.searchProductByName("%"+name+"%");
	}
	
    public Product getProductById(Long id) {
    	return productRepository.findProductById(id);
    }
	
	public List<Product> getListProduct() {
		return productRepository.findListProduct();
	}
	
	public List<Product> getTrashProduct() {
		return productRepository.findTrashProduct();
	}
	
	public List<Product> getFavoriteProduct(Integer userId) {
		return productRepository.findFavoriteProduct(userId);
	}
	
    public boolean addProduct(Product product) {
    	try {
    		productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteProduct(Product product) {
    	try {
    		productRepository.delete(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
