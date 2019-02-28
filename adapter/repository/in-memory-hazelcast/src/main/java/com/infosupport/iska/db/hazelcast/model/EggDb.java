package com.infosupport.iska.db.hazelcast.model;

import com.infosupport.iska.domain.entity.Egg;

import java.io.Serializable;

public class EggDb implements Serializable {

	private static final long serialVersionUID = 2L;

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

	public static EggDb from(final Egg egg) {
		var eggDb = new EggDb();
		eggDb.setNumber(egg.getNumber());
		return eggDb;
	}


}
