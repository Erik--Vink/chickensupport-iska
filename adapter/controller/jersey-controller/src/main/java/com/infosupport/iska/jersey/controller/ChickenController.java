package com.infosupport.iska.jersey.controller;

import com.infosupport.iska.domain.entity.Egg;
import com.infosupport.iska.jersey.model.ChickenDto;
import com.infosupport.iska.jersey.model.EggDto;
import com.infosupport.iska.usecase.CreateChicken;
import com.infosupport.iska.usecase.FindChicken;
import com.infosupport.iska.usecase.LayEggsForChicken;

import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.Startup;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Startup
@DependsOn("ResourceBinder")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("/chickens")
public class ChickenController {

	@Resource(mappedName = "CreateChicken")
	private CreateChicken createChicken;
	@Resource(mappedName = "FindChicken")
	private FindChicken findChicken;
	@Resource(mappedName = "LayEggsForChicken")
	private LayEggsForChicken layEggsForChicken;

	public ChickenController() {
	}

	@POST
	public ChickenDto createChicken(@Valid final ChickenDto chickenDto) {
		var chicken = chickenDto.toChicken();
		return ChickenDto.toChickenDto(createChicken.create(chicken));
	}

	@GET
	public List<ChickenDto> allChickens() {
		return findChicken.findAllChickens()
			.stream()
			.map(ChickenDto::toChickenDto)
			.collect(Collectors.toList());
	}

	@POST
	@Path("/{registrationNumber}/eggs")
	public List<EggDto> layEgg(@PathParam("registrationNumber") final String registrationNumber, @Valid final EggDto eggDto) {
		ArrayList<Egg> eggs = new ArrayList<>();

		var egg = eggDto.toEgg();
		if (egg != null) {
			eggs.add(egg);
		}

		List<Egg> createdEggs = layEggsForChicken.layEggsForChicken(registrationNumber, eggs);
		return createdEggs.stream().map(EggDto::toEggDto).collect(Collectors.toList());
	}
}
