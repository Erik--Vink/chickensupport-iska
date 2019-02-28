package com.infosupport.iska.domain.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Chicken {

	private String registrationNumber;
	private String name;
	private Gender gender;
	private int age;
	private List<Egg> eggs = new ArrayList<>();

	public Chicken(String registrationNumber, String name, Gender gender, int age) {
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public Chicken(String registrationNumber, String name, Gender gender, int age, List<Egg> eggs) {
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.gender = gender;
		this.age = age;
		if (eggs != null) {
			this.eggs = eggs;
		}
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public void addEgg(Egg egg) {
		this.eggs.add(egg);
	}

	public List<Egg> getEggs() {
		return Collections.unmodifiableList(eggs);
	}

	public static ChickenBuilder builder() {
		return new ChickenBuilder();
	}

	public static class ChickenBuilder {
		private String registrationNumber;
		private String name;
		private Gender gender;
		private int age;
		private List<Egg> eggs;

		ChickenBuilder() {
		}

		public ChickenBuilder withRegistrationNumber(final String registrationNumber) {
			this.registrationNumber = registrationNumber;
			return this;
		}

		public ChickenBuilder withName(final String name) {
			this.name = name;
			return this;
		}

		public ChickenBuilder withGender(final String gender) {
			if (gender != null) {
				this.gender = Gender.valueOf(gender);
			}
			return this;
		}

		public ChickenBuilder withGender(final Gender gender) {
			this.gender = gender;
			return this;
		}

		public ChickenBuilder withAge(final int age) {
			this.age = age;
			return this;
		}

		public ChickenBuilder withEggs(final List<Egg> eggs) {
			this.eggs = eggs;
			return this;
		}

		public Chicken build() {
			return new Chicken(registrationNumber, name, gender, age, eggs);
		}
	}

	@Override
	public String toString() {
		String eggObjects = eggs.stream()
			.map(Object::toString)
			.collect(Collectors.joining(",\n"));

		return "Chicken{" +
			"registrationNumber='" + registrationNumber + '\'' +
			", name='" + name + '\'' +
			", gender='" + gender.toString() + '\'' +
			", age='" + age + '\'' +
			", eggs=[\n" + eggObjects + "\n]" +
			'}';
	}
}
