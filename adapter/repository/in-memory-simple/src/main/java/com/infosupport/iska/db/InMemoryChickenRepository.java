package com.infosupport.iska.db;

import com.infosupport.iska.domain.entity.Chicken;
import com.infosupport.iska.domain.entity.Egg;
import com.infosupport.iska.domain.port.ChickenRepository;

import java.util.*;

public class InMemoryChickenRepository implements ChickenRepository {

	private final Map<String, Chicken> inMemoryDb = new HashMap<>();

	@Override
	public Chicken create(final Chicken chicken) {
		inMemoryDb.put(chicken.getRegistrationNumber(), chicken);
		return chicken;
	}

	@Override
	public Optional<Chicken> findByRegistrationNumber(String registrationNumber) {
		return Optional.ofNullable(inMemoryDb.get(registrationNumber));
	}

	@Override
	public Optional<Chicken> findByName(final String name) {
		return inMemoryDb.values().stream()
			.filter(user -> user.getName().equals(name))
			.findAny();

	}

	@Override
	public List<Chicken> findAllChickens() {
		return new ArrayList<>(inMemoryDb.values());
	}

	@Override
	public void addEggs(Chicken chicken, List<Egg> eggs) {
		Optional<Chicken> existingChickenOptional = findByRegistrationNumber(chicken.getRegistrationNumber());
		if (existingChickenOptional.isPresent()) {
			Chicken existingChicken = existingChickenOptional.get();
			eggs.forEach(existingChicken::addEgg);
			inMemoryDb.put(existingChicken.getRegistrationNumber(), existingChicken);
		}
	}
}
