package com.locknlock.locknlocks.locknlocks.model;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "role_name", length = 20)
	private ERole name;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "trash")
	private Boolean trash;
	
	public Role() {

	}
	
	public Role(ERole name) {
		this.name = name;
		this.status = true;
		this.trash = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
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

}