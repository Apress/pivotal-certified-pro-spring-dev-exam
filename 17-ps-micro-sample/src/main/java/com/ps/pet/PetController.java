package com.ps.pet;

import com.ps.ex.PetNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by iuliana.cosmina on 10/24/16.
 */
@RestController
@RequestMapping("/pets")
public class PetController {

	private static Logger logger = LoggerFactory.getLogger(PetController.class);
	@Autowired
	PetRepo petRepository;

	@RequestMapping("/")
	public List<Pet> all() {
		List<Pet> pets = petRepository.findAll();
		logger.info("Pest extracted");
		if (pets.isEmpty())
			throw new PetNotFoundException("all");
		else {
			return pets;
		}
	}

	@RequestMapping("/{rfid}")
	public Pet byRfid(@PathVariable("rfid") String rfid) {
		Pet pet = petRepository.findByRfId(rfid);
		if (pet == null)
			throw new PetNotFoundException(rfid);
		else {
			return pet;
		}
	}

	@RequestMapping("/type/{type}")
	public List<Pet> byPetType(@PathVariable("type") String type) {
		PetType petType  = null;
		try {
			petType= PetType.valueOf(type);
		} catch (Exception e) {
			throw new PetNotFoundException("Invalid type:" + type.toString());
		}
		List<Pet> pets = petRepository.findAllByType(petType);
		logger.info("Pets found for type" + type + ", " + pets);
		if (pets.isEmpty())
			throw new PetNotFoundException(type.toString());
		else {
			return pets;
		}
	}

	@RequestMapping("/owner/{id}")
	public List<Pet> byOwner(@PathVariable("id") Long ownerId) {
		List<Pet> pets = petRepository
				.findAllByOwner(ownerId);
		logger.info("Pets found for owner" + ownerId + ", " + pets);
		if (pets.isEmpty())
			throw new PetNotFoundException(ownerId.toString());
		else {
			return pets;
		}
	}
}
