package com.infosupport.iska.usecase.validator;

import com.infosupport.iska.domain.entity.Chicken;
import com.infosupport.iska.domain.exception.ChickenValidationException;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class ChickenValidator {

	public static void validateCreateChicken(final Chicken chicken) {
		if (chicken == null) throw new ChickenValidationException("Chicken should not be null");
		if (isBlank(chicken.getName())) throw new ChickenValidationException("Name should not be null");
	}

	private ChickenValidator() {

	}
}
