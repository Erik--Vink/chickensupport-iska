package com.infosupport.iska.vertx.controller;

import com.infosupport.iska.domain.entity.Egg;
import com.infosupport.iska.usecase.CreateChicken;
import com.infosupport.iska.usecase.FindChicken;
import com.infosupport.iska.usecase.LayEggsForChicken;
import com.infosupport.iska.vertx.model.ChickenDto;
import com.infosupport.iska.vertx.model.EggDto;
import com.infosupport.iska.vertx.utils.JsonCollectors;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;

public class VertxChickenController {

	private final CreateChicken createChicken;
	private final FindChicken findChicken;
	private final LayEggsForChicken layEggsForChicken;

	public VertxChickenController(final CreateChicken createChicken, final FindChicken findChicken, final LayEggsForChicken layEggsForChicken) {
		this.createChicken = createChicken;
		this.findChicken = findChicken;
		this.layEggsForChicken = layEggsForChicken;
	}

	public void createChicken(final RoutingContext routingContext) {
		var response = routingContext.response();
		var body = routingContext.getBody();
		if (isNull(body)) {
			sendError(400, response);
		} else {
			var chickenDto = body.toJsonObject().mapTo(ChickenDto.class);
			var chicken = createChicken.create(chickenDto.toChicken());
			var result = JsonObject.mapFrom(ChickenDto.toChickenDto(chicken));
			sendSuccess(result, response);
		}
	}

	public void findChickenByRegistrationNumber(final RoutingContext routingContext) {
		var response = routingContext.response();
		var registrationNumber = routingContext.request().getParam("registrationNumber");
		if (registrationNumber == null) {
			sendError(400, response);
		} else {
			var chicken = findChicken.findByRegistrationNumber(registrationNumber);
			if (chicken.isPresent()) {
				var result = JsonObject.mapFrom(ChickenDto.toChickenDto(chicken.get()));
				sendSuccess(result, response);
			} else {
				sendError(404, response);
			}
		}
	}

	public void findAllChickens(final RoutingContext routingContext) {
		var response = routingContext.response();
		var chickens = findChicken.findAllChickens();
		var result = chickens.stream()
			.map(chicken -> JsonObject.mapFrom(ChickenDto.toChickenDto(chicken)))
			.collect(JsonCollectors.toJsonArray());
		response
			.putHeader("content-type", "application/json")
			.end(result.encodePrettily());
	}

	public void layEgg(final RoutingContext routingContext) {
		var response = routingContext.response();
		var registrationNumber = routingContext.request().getParam("registrationNumber");
		var body = routingContext.getBody();
		if (isNull(body)) {
			sendError(400, response);
		} else {
			var eggDto = body.toJsonObject().mapTo(EggDto.class);
			ArrayList<Egg> eggs = new ArrayList<>();
			var egg = eggDto.toEgg();
			if (egg != null) {
				eggs.add(egg);
			}

			var createdEggs = layEggsForChicken.layEggsForChicken(registrationNumber, eggs);
			var result = createdEggs.stream()
				.map(e -> JsonObject.mapFrom(EggDto.toEggDto(e)))
				.collect(JsonCollectors.toJsonArray());

			response
				.putHeader("content-type", "application/json")
				.end(result.encodePrettily());
		}
	}

	private boolean isNull(final Buffer buffer) {
		return buffer == null || "".equals(buffer.toString());
	}

	private void sendError(int statusCode, HttpServerResponse response) {
		response
			.putHeader("content-type", "application/json")
			.setStatusCode(statusCode)
			.end();
	}

	private void sendSuccess(JsonObject body, HttpServerResponse response) {
		response
			.putHeader("content-type", "application/json")
			.end(body.encodePrettily());
	}
}
