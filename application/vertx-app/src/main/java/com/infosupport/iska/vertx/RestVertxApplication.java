package com.infosupport.iska.vertx;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.infosupport.iska.config.VertxConfig;
import com.infosupport.iska.vertx.controller.VertxChickenController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class RestVertxApplication extends AbstractVerticle {

	private final VertxChickenController chickenController = new VertxConfig().getVertxChickenController();

	@Override
	public void start() {
		Json.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		var router = Router.router(vertx);
		router.route().handler(BodyHandler.create());

		router.post("/chickens").handler(chickenController::createChicken);
		router.get("/chickens").handler(chickenController::findAllChickens);
		router.get("/chickens/:registrationNumber").handler(chickenController::findChickenByRegistrationNumber);
		router.post("/chickens/:registrationNumber/eggs").handler(chickenController::layEgg);

		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}

	public static void main(final String[] args) {
		Launcher.executeCommand("run", RestVertxApplication.class.getName());
	}
}
