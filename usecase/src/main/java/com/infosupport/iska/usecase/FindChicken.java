package com.infosupport.iska.usecase;

import com.infosupport.iska.domain.entity.Chicken;
import com.infosupport.iska.domain.port.ChickenRepository;

import java.util.List;
import java.util.Optional;

public final class FindChicken {

	private final ChickenRepository repository;

	public FindChicken(final ChickenRepository repository) {
		this.repository = repository;
	}

	public Optional<Chicken> findByRegistrationNumber(final String registrationNumber) {
		return repository.findByRegistrationNumber(registrationNumber);
	}

	public List<Chicken> findAllChickens() {
		return repository.findAllChickens();
	}
}
