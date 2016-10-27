package com.ps.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by iuliana.cosmina on 10/24/16.
 */
@Table(name = "P_USER")
@Entity
public class User {

	@Id
	private Long id;
	private String username;
	private String email;

	public User() {
	}

	public User(Long id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
