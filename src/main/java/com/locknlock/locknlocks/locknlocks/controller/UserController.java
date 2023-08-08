package com.locknlock.locknlocks.locknlocks.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.locknlock.locknlocks.locknlocks.model.ERole;
import com.locknlock.locknlocks.locknlocks.model.Role;
import com.locknlock.locknlocks.locknlocks.model.User;
import com.locknlock.locknlocks.locknlocks.payload.request.UserRequest;
import com.locknlock.locknlocks.locknlocks.repository.RoleRepository;
import com.locknlock.locknlocks.locknlocks.repository.UserRepository;
import com.locknlock.locknlocks.locknlocks.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/")
public class UserController {
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("{id}")
	public User getUserById(@PathVariable(value="id") Long userId) {
		return userService.getUserById(userId);
	}
	
	@GetMapping("role")
	public List<User> getByRoles_Name() {
		return userService.getByRoles_Name();
	}
	
	@GetMapping("email/{email}")
	public User getUserByEmail(@PathVariable(value="email") String email) {
		return userService.getUserByEmail(email);
	}
	
	@GetMapping("list")
	public List<User> getListUser() {
		return this.userService.getListUser();
	}
	
	@GetMapping("date")
	public List<User> getListUserByDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		return this.userService.getListUserByDate(startDate, endDate);
	}
	
	@GetMapping("trash")
	public List<User> getTrashUser() {
		return this.userService.getTrashUser();
	}
	
	@PostMapping("add")
	public Map<String, Object> addUser(@Valid @RequestBody UserRequest userDetail){
		Map<String, Object> response = new HashMap<>();

		if (userRepository.existsByUsername(userDetail.getUsername())) {
		    response.put("status", false);
		    response.put("message", "UserName đã tồn tại!");
		    return response;
		}
		
		if (userRepository.existsByEmail(userDetail.getEmail())) {
			response.put("status", false);
		    response.put("message", "Email đã tồn tại!");
		    return response;
		}
		
		User user = new User(
				userDetail.getUsername(),
				userDetail.getPhone(),
				userDetail.getEmail(),
				encoder.encode(userDetail.getPassword()));
		
		Set<String> strRoles = userDetail.getRole();
		Set<Role> roles = new HashSet<>();
		
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				case "seller":
					Role selRole = roleRepository.findByName(ERole.ROLE_SELLER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(selRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		
		response.put("status", true);
	    response.put("message", "Thêm người dùng thành công!");
	    return response;
	}
	
	@PutMapping("{id}")
    public Map<String, Object> updateUser(@PathVariable(value = "id") Long userId, @Validated @RequestBody UserRequest userDetail){
		Map<String, Object> response = new HashMap<>();
        User user = userService.getUserById(userId);
        if(user != null) {
    		if (userRepository.existsByUsername(userDetail.getUsername())) {
    		    response.put("status", false);
    		    response.put("message", "UserName đã tồn tại!");
    		    return response;
    		}
    		
    		if (userRepository.existsByEmail(userDetail.getEmail())) {
    			response.put("status", false);
    		    response.put("message", "Email đã tồn tại!");
    		    return response;
    		}
    		
    		user.setUsername(userDetail.getUsername());
    		user.setPhone(userDetail.getPhone());
    		user.setPassword(encoder.encode(userDetail.getPassword()));
    		user.setEmail(userDetail.getEmail());
    		
    		Set<String> strRoles = userDetail.getRole();
    		Set<Role> roles = new HashSet<>();
    		
    		if (strRoles == null) {
    			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
    					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    			roles.add(userRole);
    		} else {
    			strRoles.forEach(role -> {
    				switch (role) {
    				case "admin":
    					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
    							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    					roles.add(adminRole);
    					break;
    				case "seller":
    					Role selRole = roleRepository.findByName(ERole.ROLE_SELLER)
    							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    					roles.add(selRole);

    					break;
    				case "mod":
    					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
    							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    					roles.add(modRole);

    					break;
    				default:
    					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
    							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    					roles.add(userRole);
    				}
    			});
    		}

    		user.setRoles(roles);
    		userService.addUser(user);
    		response.put("status", true);
    	    response.put("message", "Chỉnh sửa người dùng thành công!");
    	    return response;
        }else {
    		response.put("status", false);
    	    response.put("message", "Người dùng không tồn tại!");
    	    return response;
        }

    }
	
	@PatchMapping("{id}")
	public Map<String, Object> updateUserStatusAndTrash(@PathVariable(value = "id") Long userId, @Validated @RequestBody UserRequest userDetail) {
		User user = userService.getUserById(userId);
		Map<String, Object> response = new HashMap<>();
		if(user != null) {
			user.setStatus(userDetail.getStatus());
			user.setTrash(userDetail.getTrash());
			userService.addUser(user);
    		response.put("status", true);
    	    response.put("message", "Chỉnh sửa người dùng thành công!");
    	    return response;
		}else {
			response.put("status", false);
    	    response.put("message", "Người dùng không tồn tại!");
    	    return response;
		}
	}
	
	@PatchMapping("verify/{id}")
	public Map<String, Object> updateUserVerify(@PathVariable(value = "id") Long userId, @Validated @RequestBody User userDetail) {
		User user = userService.getUserById(userId);
		Map<String, Object> response = new HashMap<>();
		if(user != null) {
			user.setStatus(userDetail.getStatus());
			user.setVerify(userDetail.getVerify());
			userService.addUser(user);
    		response.put("status", true);
    	    response.put("message", "Chỉnh sửa người dùng thành công!");
    	    return response;
		}else {
			response.put("status", false);
    	    response.put("message", "Người dùng không tồn tại!");
    	    return response;
		}
	}
	
	@PatchMapping("password/{id}")
	public Map<String, Object> updateUserPassword(@PathVariable(value = "id") Long userId, @Validated @RequestBody User userDetail) {
		User user = userService.getUserById(userId);
		Map<String, Object> response = new HashMap<>();
		if(user != null) {
			user.setPassword(encoder.encode(userDetail.getPassword()));
			userService.addUser(user);
    		response.put("status", true);
    	    response.put("message", "Chỉnh sửa người dùng thành công!");
    	    return response;
		}else {
			response.put("status", false);
    	    response.put("message", "Người dùng không tồn tại!");
    	    return response;
		}
	}
	
	
	
	@DeleteMapping("{id}")
    public Map<String, Object> deleteProduct(@PathVariable(value = "id") Long userId) {
		User user = userService.getUserById(userId);
		Map<String, Object> response = new HashMap<>();
		if(user != null) {
			userService.deleteUser(user);
			response.put("status", true);
    	    response.put("message", "Xóa người dùng thành công!");
    	    return response;
		}else {
			response.put("status", false);
    	    response.put("message", "Người dùng không tồn tại!");
    	    return response;
		}
    }

}
