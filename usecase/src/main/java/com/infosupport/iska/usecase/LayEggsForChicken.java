package com.infosupport.iska.usecase;

import com.infosupport.iska.domain.entity.Chicken;
import com.infosupport.iska.domain.entity.Egg;
import com.infosupport.iska.domain.exception.ChickenNotFoundException;
import com.infosupport.iska.domain.port.ChickenRepository;
import com.infosupport.iska.domain.port.IdGenerator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LayEggsForChicken {

	private final ChickenRepository chickenRepository;
	private final IdGenerator idGenerator;

	public LayEggsForChicken(final ChickenRepository chickenRepository, final IdGenerator idGenerator) {
		this.chickenRepository = chickenRepository;
		this.idGenerator = idGenerator;
	}

	public List<Egg> layEggsForChicken(final String registrationNumber, final List<Egg> eggs) {
		if (chickenRepository.findByRegistrationNumber(registrationNumber).isEmpty()) {
			throw new ChickenNotFoundException(registrationNumber);
		}

		Chicken chicken = chickenRepository.findByRegistrationNumber(registrationNumber).get();

		if (!eggs.isEmpty()) {
			chickenRepository.addEggs(chicken, eggs);
			return eggs;
		} else {
			List<Egg> generatedEggList = generateRandomListOfEggsForChicken();
			chickenRepository.addEggs(chicken, generatedEggList);
			return generatedEggList;
		}
	}

	private List<Egg> generateRandomListOfEggsForChicken() {
		Random random = new Random();
		int numberOfEggs = random.ints(1, 10).findFirst().getAsInt();

		return IntStream.range(0, numberOfEggs).
			mapToObj((number) -> new Egg(idGenerator.generate()))
			.collect(Collectors.toList());
	}
}
