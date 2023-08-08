package com.locknlock.locknlocks.locknlocks.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;

import com.locknlock.locknlocks.locknlocks.dto.BestSellerProducts;
import com.locknlock.locknlocks.locknlocks.model.Product;
import com.locknlock.locknlocks.locknlocks.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product/")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("new/{limit}")
	public List<Product> getLatestActiveProducts(@PathVariable(value = "limit")Integer limit) {
		return productService.getLatestActiveProducts(limit);
	}

	@GetMapping("banner")
	public List<Product> getProductByBannerId() {
		return productService.getProductByBannerId();
	}

	@GetMapping("dealhot/{limit}")
	public List<Product> getDealHotProducts(@PathVariable(value = "limit")Integer limit) {
		return productService.getDealHotProducts(limit);
	}
	
	@GetMapping("bestseller/{limit}")
	public List<BestSellerProducts> getBestSellerProducts(@PathVariable(value = "limit")Integer limit) {
		return productService.getBestSellerProducts(limit);
	}
	
	@GetMapping("favorite/{userId}")
	public List<Product> getFavoriteProduct(@PathVariable(value = "userId")Integer userId) {
		return productService.getFavoriteProduct(userId);
	}

	@GetMapping("category/{id}")
	public List<Product> getByCategoryId(@PathVariable(value = "id") Long id) {
		return productService.getByCategoryId(id);
	}

	@GetMapping("parentcategory/{id}")
	public List<Product> getProductByParentCategoryId(@PathVariable(value = "id") Long id) {
		return productService.getProductByParentCategoryId(id);
	}

	@GetMapping("search/{name}")
	public List<Product> getProductByName(@PathVariable String name) {
		return productService.getProductByName(name);
	}

	@GetMapping("{id}")
	public Product getProductById(@PathVariable(value = "id") Long productId) {
		return productService.getProductById(productId);
	}

	@GetMapping("list")
	public List<Product> getListProduct() {
		return this.productService.getListProduct();
	}

	@GetMapping("trash")
	public List<Product> getTrashProduct() {
		return this.productService.getTrashProduct();
	}

	@PatchMapping("quantity/{id}")
	public boolean updateProductQuantity(@PathVariable(value = "id") Long productId,
			@Validated @RequestBody Product productDetail) {
		Product product = productService.getProductById(productId);
		if (product != null) {
			product.setQuantity(productDetail.getQuantity());
			return productService.addProduct(product);
		} else {
			return false;
		}
	}
	
	@PostMapping("add")
	public boolean addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@PutMapping("quantity/{id}/{quantity}")
	public ResponseEntity<String> updateProductQuantity(
	    @PathVariable Long id, 
	    @PathVariable int quantity) {
	    Product product = productService.getProductById(id);
	    if (product != null) {
	        int currentQuantity = product.getQuantity();
	        
	        int updatedQuantity = currentQuantity - quantity;
	        if (updatedQuantity >= 0) {
	            product.setQuantity(updatedQuantity);
	            productService.addProduct(product);
	            return ResponseEntity.ok("Cập nhật số lượng sản phẩm thành công");
	        } else {
	            return ResponseEntity.badRequest().body("Số lượng sản phẩm không đủ");
	        }
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	@PutMapping("favorite/{id}")
	public boolean updateProductFavorite(@PathVariable(value = "id") Long id,
			@Validated @RequestBody Product productDetail) {
		Product product = productService.getProductById(id);
		if (product != null) {
			long currentFavorite = product.getFavoriteCount();
			long updateFavorite = currentFavorite + productDetail.getFavoriteCount();
			product.setFavoriteCount(updateFavorite);
			productService.addProduct(product);
			return true;
		} else {
			return false;
		}
	}

	@PutMapping("{id}")
	public boolean updateProduct(@PathVariable(value = "id") Long productId,
			@Validated @RequestBody Product productDetail) {
		Product product = productService.getProductById(productId);
		if (product != null) {
			product.setProductName(productDetail.getProductName());
			product.setCategoryId(productDetail.getCategoryId());
			product.setBannerId(productDetail.getBannerId());
			product.setImage(productDetail.getImage());
			product.setImportPrice(productDetail.getImportPrice());
			product.setPrice(productDetail.getPrice());
			product.setSale(productDetail.getSale());
			product.setDetailImage(productDetail.getDetailImage());
			product.setDetail(productDetail.getDetail());
			product.setStatus(productDetail.getStatus());
			product.setTrash(productDetail.getTrash());
			product.setQuantity(productDetail.getQuantity());
			product.setFavoriteCount(productDetail.getFavoriteCount());
			return productService.addProduct(product);
		} else {
			return false;
		}
	}

	@PatchMapping("{id}")
	public boolean updateProductStatusAndTrash(@PathVariable(value = "id") Long productId,
			@Validated @RequestBody Product productDetail) {
		Product product = productService.getProductById(productId);
		if (product != null) {
			product.setStatus(productDetail.getStatus());
			product.setTrash(productDetail.getTrash());
			return productService.addProduct(product);
		} else {
			return false;
		}
	}

	@DeleteMapping("{id}")
	public boolean deleteProduct(@PathVariable(value = "id") Long productId) {
		Product product = productService.getProductById(productId);
		if (product != null) {
			return productService.deleteProduct(product);
		} else {
			return false;
		}
	}

	@PostMapping("/upload-file")
	public String uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getName());
		System.out.println(file.getContentType());
		System.out.println(file.getSize());

		String Path_Directory = "https://f005.backblazeb2.com/file/image-api";
		Files.copy(file.getInputStream(), Paths.get(Path_Directory + File.separator + file.getOriginalFilename()),
				StandardCopyOption.REPLACE_EXISTING);
		return "Successfully Image is uploaded!";
	}

}
