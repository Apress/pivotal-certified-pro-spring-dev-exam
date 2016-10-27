package com.ps.pet;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by iuliana.cosmina on 10/24/16.
 */
@Table(name = "P_PET")
@Entity
public class Pet {

	@Id
	private Long id;
	@NotNull
	private Long ownerId;
	private String name;
	private Integer age;
	/**
	 * The pet has a RFID microchip implant.
	 */
	@NotEmpty
	@Column
	@Size(min = 10, max = 100)
	private String rfid;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "PET_TYPE")
	private PetType petType;

	public Pet() {
	}

	public Pet(Long ownerId, Long id, String name, PetType type, Integer age, String rfid) {
		this.ownerId = ownerId;
		this.id = id;
		this.name = name;
		this.age = age;
		this.rfid = rfid;
		this.petType = petType;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public PetType getPetType() {
		return petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}
}
