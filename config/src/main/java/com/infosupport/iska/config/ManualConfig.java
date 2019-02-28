package com.infosupport.iska.config;

import com.infosupport.iska.db.InMemoryChickenRepository;
import com.infosupport.iska.domain.port.ChickenRepository;
import com.infosupport.iska.domain.port.IdGenerator;
import com.infosupport.iska.usecase.CreateChicken;
import com.infosupport.iska.usecase.FindChicken;
import com.infosupport.iska.usecase.LayEggsForChicken;
import com.infosupport.iska.uuid.UuidGenerator;

public class ManualConfig {
	private final IdGenerator uuidGenerator = new UuidGenerator();
	private final ChickenRepository chickenRepository = new InMemoryChickenRepository();

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
