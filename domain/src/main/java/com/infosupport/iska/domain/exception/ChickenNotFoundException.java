package com.infosupport.iska.domain.exception;

public class ChickenNotFoundException extends RuntimeException {
	public ChickenNotFoundException(final String registrationNumber) {
		super(registrationNumber);
	}
}
