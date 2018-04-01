package com.ps.web;

import java.util.List;

/**
 * Created by iuliana.cosmina on 10/27/16.
 */
public class UserSkeleton {

	private Long id;

	private String username;

	private List<PetSkeleton> pets;

	public UserSkeleton() {
	}

	public UserSkeleton(Long id, String username) {
		this.id = id;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<PetSkeleton> getPets() {
		return pets;
	}

	public void setPets(List<PetSkeleton> pets) {
		this.pets = pets;
	}


}
