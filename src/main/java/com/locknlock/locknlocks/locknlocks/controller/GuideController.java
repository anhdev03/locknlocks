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

import com.locknlock.locknlocks.locknlocks.model.Guide;
import com.locknlock.locknlocks.locknlocks.service.GuideService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/guide/")
public class GuideController {
	@Autowired
	private GuideService guideService;
	
	@GetMapping("all")
	public List<Guide> getAllGuide() {
		return guideService.getAllGuide();
	}
	
	@GetMapping("{id}")
	public Guide getGuideById(@PathVariable(value = "id") Long id) {
		return guideService.getGuideById(id);
	}
	
	@GetMapping("list")
	public List<Guide> getListGuide() {
		return this.guideService.getListGuide();
	}

	@GetMapping("trash")
	public List<Guide> getTrashGuide() {
		return this.guideService.getTrashGuide();
	}
	
	@PostMapping("add")
	public boolean addGuide(@RequestBody Guide guide) {
		return guideService.addGuide(guide);
	}
	
	@PutMapping("{id}")
	public boolean updateGuide(@PathVariable(value = "id") Long guideId,
			@Validated @RequestBody Guide guideDetail) {
		Guide guide = guideService.getGuideById(guideId);
		if (guide != null) {
			guide.setImage(guideDetail.getImage());
			guide.setLink(guideDetail.getLink());
			guide.setName(guideDetail.getName());
			guide.setStatus(guideDetail.getStatus());
			guide.setTrash(guideDetail.getTrash());
			return guideService.addGuide(guide);
		} else {
			return false;
		}
	}
	
	@PatchMapping("{id}")
	public boolean updateGuideStatusAndTrash(@PathVariable(value = "id") Long guideId,
			@Validated @RequestBody Guide guideDetail) {
		Guide guide = guideService.getGuideById(guideId);
		if (guide != null) {
			guide.setStatus(guideDetail.getStatus());
			guide.setTrash(guideDetail.getTrash());
			return guideService.addGuide(guide);
		} else {
			return false;
		}
	}
	
	@DeleteMapping("{id}")
	public boolean deleteGuide(@PathVariable(value = "id") Long guideId) {
		Guide guide = guideService.getGuideById(guideId);
		if (guide != null) {
			return guideService.deleteGuide(guide);
		} else {
			return false;
		}
	}
}
