package com.infosupport.iska.db.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.infosupport.iska.db.hazelcast.model.ChickenDb;
import com.infosupport.iska.domain.entity.Chicken;
import com.infosupport.iska.domain.entity.Egg;
import com.infosupport.iska.domain.port.ChickenRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HazelcastChickenRepository implements ChickenRepository {

	private static final HazelcastInstance HAZELCAST = Hazelcast.getInstance();
	private static final String MAP_NAME = "chicken";

	@Override
	public Chicken create(final Chicken chicken) {
		var chickenDb = ChickenDb.from(chicken);
		var map = HAZELCAST.getMap(MAP_NAME);
		map.put(chickenDb.getRegistrationNumber(), chickenDb);
		return chicken;
	}

	@Override
	public Optional<Chicken> findByRegistrationNumber(String registrationNumber) {
		var map = HAZELCAST.<String, ChickenDb>getMap(MAP_NAME);
		if (map.containsKey(registrationNumber)) {
			var chickenDb = map.get(registrationNumber);
			return Optional.of(chickenDb.toChicken());
		}
		return Optional.empty();
	}

	@Override
	public Optional<Chicken> findByName(final String name) {
		return HAZELCAST.<String, ChickenDb>getMap(MAP_NAME)
			.values().stream()
			.filter(chickenDb -> chickenDb.toChicken().getName().equals(name))
			.map(ChickenDb::toChicken)
			.findAny();
	}

	@Override
	public List<Chicken> findAllChickens() {
		return HAZELCAST.<String, ChickenDb>getMap(MAP_NAME)
			.values()
			.stream()
			.map(ChickenDb::toChicken)
			.collect(Collectors.toList());
	}

	@Override
	public void addEggs(Chicken chicken, List<Egg> eggs) {
		var map = HAZELCAST.getMap(MAP_NAME);

		Optional<Chicken> existingChickenOptional = findByRegistrationNumber(chicken.getRegistrationNumber());
		if (existingChickenOptional.isPresent()) {
			Chicken existingChicken = existingChickenOptional.get();
			eggs.forEach(existingChicken::addEgg);
			map.replace(existingChicken.getRegistrationNumber(), ChickenDb.from(existingChicken));
		}
	}
}
