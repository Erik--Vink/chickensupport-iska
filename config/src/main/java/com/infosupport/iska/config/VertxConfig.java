package com.infosupport.iska.config;

import com.infosupport.iska.db.InMemoryChickenRepository;
import com.infosupport.iska.db.hazelcast.HazelcastChickenRepository;
import com.infosupport.iska.domain.port.ChickenRepository;
import com.infosupport.iska.domain.port.IdGenerator;
import com.infosupport.iska.jug.JugIdGenerator;
import com.infosupport.iska.usecase.*;
import com.infosupport.iska.uuid.UuidGenerator;
import com.infosupport.iska.vertx.controller.VertxChickenController;

public class VertxConfig {

	private final IdGenerator jugIdGenerator = new UuidGenerator();

	private final ChickenRepository chickenRepository = new InMemoryChickenRepository();
	private final CreateChicken createChicken = new CreateChicken(chickenRepository, jugIdGenerator);
	private final FindChicken findChicken = new FindChicken(chickenRepository);
	private final LayEggsForChicken layEggsForChicken = new LayEggsForChicken(chickenRepository, jugIdGenerator);
	private final VertxChickenController chickenController = new VertxChickenController(createChicken, findChicken, layEggsForChicken);

	public VertxChickenController getVertxChickenController() {
		return chickenController;
	}
}
