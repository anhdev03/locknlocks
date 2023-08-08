package com.locknlock.locknlocks.locknlocks.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name="user_name")
    private String username;
    
    @Column(name="password")
    private String password;
    
    @Column(name="phone")
    private String phone;
    
    @Column(name="email")
    private String email;
    
    @Column(name="verify")
    private String verify;
    
	@Column(name="status")
	private Boolean status;
	
	@Column(name="trash")
	private Boolean trash;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_role", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
		
	}

	public User(String username, String phone, String password) {
		super();
		this.username = username;
		this.phone = phone;
		this.password = password;
		this.status = true;
		this.trash = false;
	}
	
	public User(String username, String phone, String email,  String password) {
		super();
		this.username = username;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.status = true;
		this.trash = false;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

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


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
