package com.infosupport.iska.config;

import com.infosupport.iska.db.InMemoryChickenRepository;
import com.infosupport.iska.domain.port.ChickenRepository;
import com.infosupport.iska.usecase.CreateChicken;
import com.infosupport.iska.usecase.FindChicken;
import com.infosupport.iska.usecase.LayEggsForChicken;
import com.infosupport.iska.uuid.UuidGenerator;

public class JavaEEConfig {
	private final ChickenRepository chickenRepository = new InMemoryChickenRepository();
	private final UuidGenerator uuidGenerator = new UuidGenerator();

	public CreateChicken createChicken() {
		return new CreateChicken(chickenRepository, uuidGenerator);
	}

	public FindChicken findChicken() {
		return new FindChicken(chickenRepository);
	}

	public LayEggsForChicken layEggsForChicken() {
		return new LayEggsForChicken(chickenRepository, uuidGenerator);
	}

}
