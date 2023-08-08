package com.locknlock.locknlocks.locknlocks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.locknlock.locknlocks.locknlocks.model.Banner;
import com.locknlock.locknlocks.locknlocks.service.BannerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/banner/")
public class BannerController {
	@Autowired
	private BannerService bannerService;
	
	@GetMapping("all")
    public List<Banner> getActiveBanners() {
        return bannerService.getActiveBanners();
    }
	
	@GetMapping("{id}")
	public Banner getBannerById(@PathVariable(value = "id") Long bannerId) {
		return bannerService.getBannerById(bannerId);
	}

	@GetMapping("list")
	public List<Banner> getListBanner() {
		return bannerService.getListBanner();
	}

	@GetMapping("trash")
	public List<Banner> getTrashBanner() {
		return bannerService.getTrashBanner();
	}

	@PostMapping("add")
	public boolean addBanner(@RequestBody Banner banner) {
		return bannerService.addBanner(banner);
	}

	@PutMapping("{id}")
	public boolean updateBanner(@PathVariable(value = "id") Long bannerId,
			@Validated @RequestBody Banner bannerDetail) {
		Banner banner = bannerService.getBannerById(bannerId);
		if (banner != null) {
			banner.setImage(bannerDetail.getImage());
			banner.setStatus(bannerDetail.isStatus());
			banner.setTrash(bannerDetail.isTrash());
			return bannerService.addBanner(banner);
		} else {
			return false;
		}
	}

	@PatchMapping("{id}")
	public boolean updateBannerStatusAndTrash(@PathVariable(value = "id") Long bannerId,
			@Validated @RequestBody Banner bannerDetail) {
		Banner banner = bannerService.getBannerById(bannerId);
		if (banner != null) {
			banner.setStatus(bannerDetail.isStatus());
			banner.setTrash(bannerDetail.isTrash());
			return bannerService.addBanner(banner);
		} else {
			return false;
		}
	}

	@DeleteMapping("{id}")
	public boolean deleteBanner(@PathVariable(value = "id") Long bannerId) {
		Banner banner = bannerService.getBannerById(bannerId);
		if (banner != null) {
			return bannerService.deleteBanner(banner);
		} else {
			return false;
		}
	}
}
