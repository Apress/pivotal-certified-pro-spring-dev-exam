package com.ps.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by iuliana.cosmina on 10/26/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PetNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PetNotFoundException(String rfId) {
		super("No pet found for : " + rfId);
	}
}
