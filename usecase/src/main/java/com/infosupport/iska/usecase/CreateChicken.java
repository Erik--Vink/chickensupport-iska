package com.infosupport.iska.usecase;

import com.infosupport.iska.domain.entity.Chicken;
import com.infosupport.iska.domain.exception.ChickenAlreadyExistsException;
import com.infosupport.iska.domain.port.ChickenRepository;
import com.infosupport.iska.domain.port.IdGenerator;
import com.infosupport.iska.usecase.validator.ChickenValidator;

public final class CreateChicken {

	private final ChickenRepository repository;
	private final IdGenerator idGenerator;

	public CreateChicken(final ChickenRepository repository, final IdGenerator idGenerator) {
		this.repository = repository;
		this.idGenerator = idGenerator;
	}

	public Chicken create(final Chicken chicken) {
		ChickenValidator.validateCreateChicken(chicken);
		if (chicken.getRegistrationNumber() != null) {
			if (repository.findByRegistrationNumber(chicken.getRegistrationNumber()).isPresent()) {
				throw new ChickenAlreadyExistsException(chicken.getRegistrationNumber());
			}
		}

		var chickenToSave = Chicken.builder()
			.withRegistrationNumber(idGenerator.generate())
			.withName(chicken.getName())
			.withAge(chicken.getAge())
			.withGender(chicken.getGender())
			.build();
		return repository.create(chickenToSave);
	}
}
