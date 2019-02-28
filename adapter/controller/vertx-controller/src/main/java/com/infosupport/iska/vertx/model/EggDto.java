package com.infosupport.iska.vertx.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.infosupport.iska.domain.entity.Egg;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EggDto {
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Egg toEgg() {
		if (number != null) {
			return new Egg(number);
		} else {
			return null;
		}
	}

	public static EggDto toEggDto(final Egg egg) {
		var eggDto = new EggDto();
		eggDto.setNumber(egg.getNumber());
		return eggDto;
	}


}
