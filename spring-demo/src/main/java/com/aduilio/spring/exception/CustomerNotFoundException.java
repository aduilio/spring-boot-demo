package com.aduilio.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7980208164179909508L;

	public CustomerNotFoundException(final Long id) {
		super("Customer with id " + id + " does not exist");
	}
}