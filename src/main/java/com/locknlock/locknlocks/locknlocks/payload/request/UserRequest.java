package com.locknlock.locknlocks.locknlocks.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;

public class UserRequest {
    @NotBlank
    private String username;
    
    @NotBlank
    private String address;
    
    @NotBlank
    private String phone;
    
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    private String password;
    
    private Boolean status;
    
    private Boolean trash;
    

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getTrash() {
		return trash;
	}

	public void setTrash(Boolean trash) {
		this.trash = trash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
