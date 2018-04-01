package com.ps.web;

/**
 * Created by iuliana.cosmina on 10/27/16.
 */
public class PetSkeleton {

	private String name;
	private Integer age;
	private String type;

	public PetSkeleton() {
	}

	public PetSkeleton(String name, Integer age, String type) {
		this.name = name;
		this.age = age;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
