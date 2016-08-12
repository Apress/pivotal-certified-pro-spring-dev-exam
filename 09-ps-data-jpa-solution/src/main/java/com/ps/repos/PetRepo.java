package com.ps.repos;

import com.ps.ents.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by iuliana.cosmina on 7/23/16.
 */
public interface PetRepo extends JpaRepository<Pet, Long> {
}
