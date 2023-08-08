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

import com.locknlock.locknlocks.locknlocks.model.TV;
import com.locknlock.locknlocks.locknlocks.service.TVService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tv/")
public class TVController {
	@Autowired
	private TVService tvService;
	
	@GetMapping("all")
	public List<TV> getAllTV() {
		return tvService.getAllTV();
	}
	
	@GetMapping("limit/{limit}")
	public List<TV> getAllTV(@PathVariable(value = "limit")Integer limit) {
		return tvService.getAllTV(limit);
	}
	
	@GetMapping("{id}")
	public TV getTVById(@PathVariable(value = "id") Long id) {
		return tvService.getTVById(id);
	}
	
	@GetMapping("list")
	public List<TV> getListTV() {
		return this.tvService.getListTV();
	}

	@GetMapping("trash")
	public List<TV> getTrashTV() {
		return this.tvService.getTrashTV();
	}
	
	@PostMapping("add")
	public boolean addTV(@RequestBody TV tv) {
		return tvService.addTV(tv);
	}
	
	@PutMapping("{id}")
	public boolean updateTV(@PathVariable(value = "id") Long tvId,
			@Validated @RequestBody TV tvDetail) {
		TV tv = tvService.getTVById(tvId);
		if (tv != null) {
			tv.setDetails(tvDetail.getDetails());
			tv.setImage(tvDetail.getImage());
			tv.setLink(tvDetail.getLink());
			tv.setName(tvDetail.getName());
			tv.setStatus(tvDetail.getStatus());
			tv.setTrash(tvDetail.getTrash());
			return tvService.addTV(tv);
		} else {
			return false;
		}
	}
	
	@PatchMapping("{id}")
	public boolean updateTVStatusAndTrash(@PathVariable(value = "id") Long tvId,
			@Validated @RequestBody TV tvDetail) {
		TV tv = tvService.getTVById(tvId);
		if (tv != null) {
			tv.setStatus(tvDetail.getStatus());
			tv.setTrash(tvDetail.getTrash());
			return tvService.addTV(tv);
		} else {
			return false;
		}
	}
	
	@DeleteMapping("{id}")
	public boolean deleteTV(@PathVariable(value = "id") Long tvId) {
		TV tv = tvService.getTVById(tvId);
		if (tv != null) {
			return tvService.deleteTV(tv);
		} else {
			return false;
		}
	}
	
	
}
