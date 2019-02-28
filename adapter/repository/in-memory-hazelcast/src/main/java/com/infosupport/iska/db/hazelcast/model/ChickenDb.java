package com.infosupport.iska.db.hazelcast.model;

import com.infosupport.iska.domain.entity.Chicken;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ChickenDb implements Serializable {

	private static final long serialVersionUID = 1L;

	private String registrationNumber;
	private String name;
	private int age;
	private String gender;
	private List<EggDb> eggs;

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<EggDb> getEggs() {
		return eggs;
	}

	public void setEggs(List<EggDb> eggs) {
		this.eggs = eggs;
	}

	public Chicken toChicken() {
		return Chicken.builder()
			.withRegistrationNumber(registrationNumber)
			.withName(name)
			.withAge(age)
			.withGender(gender)
			.withEggs(eggs.stream().map(EggDb::toEgg).collect(Collectors.toList()))
			.build();
	}

	public static ChickenDb from(final Chicken chicken) {
		var chickenDb = new ChickenDb();
		chickenDb.setRegistrationNumber(chicken.getRegistrationNumber());
		chickenDb.setName(chicken.getName());
		chickenDb.setAge(chicken.getAge());
		chickenDb.setEggs(chicken.getEggs().stream().map(EggDb::from).collect(Collectors.toList()));

		//Do not expose Gender because chickens also have the right to be gender neutral
		return chickenDb;
	}
}
