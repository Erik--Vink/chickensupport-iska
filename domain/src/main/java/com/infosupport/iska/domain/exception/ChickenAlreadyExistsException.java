package com.infosupport.iska.domain.exception;

public class ChickenAlreadyExistsException extends RuntimeException {
	public ChickenAlreadyExistsException(final String name) {
		super(name);
	}
}
