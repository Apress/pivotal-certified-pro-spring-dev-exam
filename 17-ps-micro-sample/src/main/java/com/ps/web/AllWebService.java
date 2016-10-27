package com.ps.web;

import com.ps.ex.UserNotFoundException;
import com.ps.pet.Pet;
import com.ps.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iuliana.cosmina on 10/27/16.
 */
@Service
public class AllWebService {

	private static Logger logger = LoggerFactory.getLogger(AllWebService.class);
	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;
	private String petsServiceUrl;
	private String usersServiceUrl;

	public AllWebService(String usersServiceUrl, String petsServiceUrl) {
		this.usersServiceUrl = usersServiceUrl;
		this.petsServiceUrl = petsServiceUrl;
	}

	public UserSkeleton findUserById(Long id) {
		logger.info("findUserById(" + id + " ) called");
		User user = null;
		try {
			 user = restTemplate.getForObject(usersServiceUrl + "/users/id/{id}", User.class, id);
		}catch (HttpClientErrorException e) {
			// no user
			throw new UserNotFoundException(id);
		}
		return new UserSkeleton(user.getId(), user.getUsername());
	}

	public List<PetSkeleton> findByOwnerId(Long ownerId) {
		logger.info("findByOwnerId(" + ownerId + " ) called");
		Pet[] pets = null;
		try {
			pets = restTemplate.getForObject(petsServiceUrl + "/pets/owner/{id}",
					com.ps.pet.Pet[].class, ownerId);
		} catch (HttpClientErrorException e) {
			// no pets
		}

		if(pets == null || pets.length ==0) {
			return null;
		}
		List<PetSkeleton> petsList = new ArrayList<>();
		for (Pet pet : pets) {
			petsList.add(new PetSkeleton(pet.getName(), pet.getAge(), pet.getPetType().toString()));
		}
		return petsList;
	}

	public List<PetSkeleton> findByType(String type) {
		logger.info("findBytype(" + type + " ) called");
		Pet[] pets = null;
		/*TODO 63. Complete the implementation of this method so that when accessing http://localhost:4002/pets/DOG
		 a page with all docs in the system will be displayed. */
		List<PetSkeleton> petsList = new ArrayList<>();
		for (Pet pet : pets) {
			petsList.add(new PetSkeleton(pet.getName(), pet.getAge(), pet.getPetType().toString()));
		}
		return petsList;
	}

}
