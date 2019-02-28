package com.infosupport.iska.spring.controller;

import com.infosupport.iska.domain.entity.Egg;
import com.infosupport.iska.spring.model.ChickenDto;
import com.infosupport.iska.spring.model.EggDto;
import com.infosupport.iska.usecase.CreateChicken;
import com.infosupport.iska.usecase.FindChicken;
import com.infosupport.iska.usecase.LayEggsForChicken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chickens")
public class ChickenController {

	private final CreateChicken createChicken;
	private final FindChicken findChicken;
	private final LayEggsForChicken layEggsForChicken;

	@Autowired
	public ChickenController(final CreateChicken createChicken, final FindChicken findChicken, final LayEggsForChicken layEggsForChicken) {
		this.createChicken = createChicken;
		this.findChicken = findChicken;
		this.layEggsForChicken = layEggsForChicken;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ChickenDto createChicken(@RequestBody final ChickenDto chickenDto) {
		var chicken = chickenDto.toChicken();
		return ChickenDto.toChickenDto(createChicken.create(chicken));
	}

	@RequestMapping(value = "/{registrationNumber}", method = RequestMethod.GET)
	public ChickenDto getChicken(@PathVariable("registrationNumber") final String registrationNumber) {
		return ChickenDto.toChickenDto(findChicken.findByRegistrationNumber(registrationNumber).orElseThrow(() -> new RuntimeException("chicken not found")));
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<ChickenDto> allChickens() {
		return findChicken.findAllChickens()
			.stream()
			.map(ChickenDto::toChickenDto)
			.collect(Collectors.toList());
	}

	@RequestMapping(value = "/{registrationNumber}/eggs", method = RequestMethod.POST)
	public List<EggDto> layEgg(@PathVariable("registrationNumber") final String registrationNumber, @RequestBody final EggDto eggDto) {
		ArrayList<Egg> eggs = new ArrayList<>();

		var egg = eggDto.toEgg();
		if (egg != null) {
			eggs.add(egg);
		}

		List<Egg> createdEggs = layEggsForChicken.layEggsForChicken(registrationNumber, eggs);
		return createdEggs.stream().map(EggDto::toEggDto).collect(Collectors.toList());
	}
}
