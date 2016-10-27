package com.ps.web;

import com.ps.pet.Pet;
import com.ps.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
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

	public UserSkeleton findUserById(Long id) {
		logger.info("findUserById(" + id + " ) called");
		User user = restTemplate.getForObject(usersServiceUrl + "/users/id/{id}", User.class, id);
		return new UserSkeleton(user.getId(), user.getUsername());
	}

	public List<PetSkeleton> findByOwnerId(Long ownerId) {
		logger.info("findByOwnerId(" + ownerId + " ) called");
		Pet[] pets = restTemplate.getForObject(petsServiceUrl + "/pets/owner/{id}",
				com.ps.pet.Pet[].class, ownerId);
		List<PetSkeleton> petsList = new ArrayList<>();
		for (Pet pet : pets) {
			petsList.add(new PetSkeleton(pet.getName(), pet.getAge(), pet.getPetType().toString()));
		}
		return petsList;
	}

	public AllWebService(String usersServiceUrl, String petsServiceUrl) {
		this.usersServiceUrl = usersServiceUrl;
		this.petsServiceUrl = petsServiceUrl;
	}
}
