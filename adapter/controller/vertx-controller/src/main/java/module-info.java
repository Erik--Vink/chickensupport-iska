module com.infosupport.iska.controller.vertx {
	exports com.infosupport.iska.vertx.controller;
	exports com.infosupport.iska.vertx.model;

	requires com.infosupport.iska.usecase;
	requires com.infosupport.iska.domain;
	requires vertx.web;
	requires vertx.core;
	requires com.fasterxml.jackson.annotation;
}
