package com.locknlock.locknlocks.locknlocks.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.locknlock.locknlocks.locknlocks.model.ERole;
import com.locknlock.locknlocks.locknlocks.model.Email;
import com.locknlock.locknlocks.locknlocks.model.Role;
import com.locknlock.locknlocks.locknlocks.model.User;
import com.locknlock.locknlocks.locknlocks.payload.request.LoginRequest;
import com.locknlock.locknlocks.locknlocks.payload.request.SignupRequest;
import com.locknlock.locknlocks.locknlocks.payload.response.JwtResponse;
import com.locknlock.locknlocks.locknlocks.payload.response.MessageResponse;
import com.locknlock.locknlocks.locknlocks.repository.RoleRepository;
import com.locknlock.locknlocks.locknlocks.repository.UserRepository;
import com.locknlock.locknlocks.locknlocks.security.jwt.JwtUtils;
import com.locknlock.locknlocks.locknlocks.security.services.UserDetailsImpl;
import com.locknlock.locknlocks.locknlocks.service.EmailSenderService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private EmailSenderService emailSenderService;
	
	@PostMapping("/send-email")
	public String sendMail(@RequestBody Email email) throws Exception {
		emailSenderService.sendEmail(email.getEmailAddress(), email.getEmailSubject(), email.getEmailBody());
		return "Send mail successfully!";
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getPhone(), 
												 userDetails.getEmail(),
												 roles));
	}
	
	@PostMapping("/signup")
	public  Map<String, Object> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		Map<String, Object> response = new HashMap<>();
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			response.put("status", false);
		    response.put("message", "UserName đã tồn tại!");
		    return response;
		}
		
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			response.put("status", false);
		    response.put("message", "Email đã tồn tại!");
		    return response;
		}
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getPhone(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
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
					Role modRole = roleRepository.findByName(ERole.ROLE_SELLER)
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
}
