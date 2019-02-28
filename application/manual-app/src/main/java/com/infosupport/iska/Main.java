package com.infosupport.iska;

import com.infosupport.iska.config.ManualConfig;
import com.infosupport.iska.domain.entity.Chicken;
import com.infosupport.iska.domain.entity.Gender;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		// Setup
		var config = new ManualConfig();
		var createChicken = config.createChicken();
		var findChicken = config.findChicken();
		var layEggsForChicken = config.layEggsForChicken();

		var chicken = Chicken.builder()
			.withName("Klara")
			.withGender(Gender.FEMALE)
			.withAge(2)
			.build();

		// Create a chicken
		var actualCreatedChicken = createChicken.create(chicken);
		System.out.println("Chicken created with registrationNumber " + actualCreatedChicken.getRegistrationNumber());

		// Find a chicken by registrationNumber
		var actualFindChicken = findChicken.findByRegistrationNumber(actualCreatedChicken.getRegistrationNumber());
		System.out.println("Found chicken with registrationNumber " + actualFindChicken.get().getRegistrationNumber());

		// List all chickens
		var chickens = findChicken.findAllChickens();
		System.out.println("List all chickens: ");
		chickens.forEach(c -> System.out.println(c.toString()));

		layEggsForChicken.layEggsForChicken(actualCreatedChicken.getRegistrationNumber(), new ArrayList<>());
		System.out.println("List all chickens: ");
		chickens.forEach(c -> System.out.println(c.toString()));
	}
}
