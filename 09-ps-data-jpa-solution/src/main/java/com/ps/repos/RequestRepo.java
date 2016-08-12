package com.ps.repos;

import com.ps.ents.Request;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by iuliana.cosmina on 7/23/16.
 */
public interface RequestRepo extends JpaRepository<Request, Long> {
}
