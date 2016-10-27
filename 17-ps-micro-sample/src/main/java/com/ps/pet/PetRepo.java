package com.ps.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by iuliana.cosmina on 10/24/16.
 */
public interface PetRepo
		extends JpaRepository<Pet, Long> {

	@Query("select p from Pet p where p.ownerId= :owner")
	List<Pet> findAllByOwner(@Param("owner") Long owner);

	@Query("select p from Pet p where p.petType= :type")
	List<Pet> findAllByType(@Param("type") PetType petType);

	@Query("select p from Pet p where p.rfid= :rfid")
	Pet findByRfId(@Param("rfid") String rfid);

	@Query("select p from Pet p")
	List<Pet> findAll();
}
