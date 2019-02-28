package com.infosupport.iska.spring.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.infosupport.iska.domain.entity.Chicken;
import com.infosupport.iska.domain.entity.Egg;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChickenDto {
	private String registrationNumber;
	private String name;
	private int age;
	private String gender;
	private List<Egg> eggs;

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

	public List<Egg> getEggs() {
		return eggs;
	}

	public void setEggs(List<Egg> eggs) {
		this.eggs = eggs;
	}

	public Chicken toChicken() {
		return Chicken.builder()
			.withRegistrationNumber(registrationNumber)
			.withName(name)
			.withAge(age)
			.withGender(gender)
			.withEggs(eggs)
			.build();
	}

	public static ChickenDto toChickenDto(final Chicken chicken) {
		var chickenDto = new ChickenDto();
		chickenDto.setRegistrationNumber(chicken.getRegistrationNumber());
		chickenDto.setName(chicken.getName());
		chickenDto.setAge(chicken.getAge());
		chickenDto.setEggs(chicken.getEggs());

		//Do not expose Gender because chickens also have the right to be gender neutral
		return chickenDto;
	}
}
