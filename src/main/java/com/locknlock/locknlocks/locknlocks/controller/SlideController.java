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

import com.locknlock.locknlocks.locknlocks.model.Slide;
import com.locknlock.locknlocks.locknlocks.service.SlideService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/slide/")
public class SlideController {
	@Autowired
	private SlideService slideService;

	@GetMapping("all")
	public List<Slide> getLastActiveSlide() {
		return slideService.getLastActiveSlide();
	}

	@GetMapping("{id}")
	public Slide getSlideById(@PathVariable(value = "id") Long slideId) {
		return slideService.getSlideById(slideId);
	}

	@GetMapping("list")
	public List<Slide> getListSlide() {
		return slideService.getListSlide();
	}

	@GetMapping("trash")
	public List<Slide> getTrashSlide() {
		return slideService.getTrashSlide();
	}

	@PostMapping("add")
	public boolean addSlide(@RequestBody Slide slide) {
		return slideService.addSlide(slide);
	}

	@PutMapping("{id}")
	public boolean updateSlide(@PathVariable(value = "id") Long slideId,
			@Validated @RequestBody Slide slideDetail) {
		Slide slide = slideService.getSlideById(slideId);
		if (slide != null) {
			slide.setImage(slideDetail.getImage());
			slide.setStatus(slideDetail.isStatus());
			slide.setTrash(slideDetail.isTrash());
			return slideService.addSlide(slide);
		} else {
			return false;
		}
	}

	@PatchMapping("{id}")
	public boolean updateSlideStatusAndTrash(@PathVariable(value = "id") Long slideId,
			@Validated @RequestBody Slide slideDetail) {
		Slide slide = slideService.getSlideById(slideId);
		if (slide != null) {
			slide.setStatus(slideDetail.isStatus());
			slide.setTrash(slideDetail.isTrash());
			return slideService.addSlide(slide);
		} else {
			return false;
		}
	}

	@DeleteMapping("{id}")
	public boolean deleteSlide(@PathVariable(value = "id") Long slideId) {
		Slide slide = slideService.getSlideById(slideId);
		if (slide != null) {
			return slideService.deleteSlide(slide);
		} else {
			return false;
		}
	}
}
