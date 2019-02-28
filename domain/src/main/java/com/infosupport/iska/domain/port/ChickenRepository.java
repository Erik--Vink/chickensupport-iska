package com.infosupport.iska.domain.port;

import com.infosupport.iska.domain.entity.Chicken;
import com.infosupport.iska.domain.entity.Egg;

import java.util.List;
import java.util.Optional;

public interface ChickenRepository {

	Chicken create(Chicken chicken);

	Optional<Chicken> findByRegistrationNumber(String registrationNumber);

	Optional<Chicken> findByName(String name);

	List<Chicken> findAllChickens();

	void addEggs(Chicken chicken, List<Egg> eggs);
}
