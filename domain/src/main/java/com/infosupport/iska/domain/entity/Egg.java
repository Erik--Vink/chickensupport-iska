package com.infosupport.iska.domain.entity;

public final class Egg {
	private String number;

	public Egg() {
	}

	public Egg(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "Egg{ number='" + number + "'}";
	}
}
