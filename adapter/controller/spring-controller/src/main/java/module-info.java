module slalom.example.controller.spring {
	exports com.infosupport.iska.spring.controller;
	exports com.infosupport.iska.spring.model;

	requires com.infosupport.iska.domain;
	requires com.infosupport.iska.usecase;
	requires spring.beans;
	requires spring.web;
	requires com.fasterxml.jackson.annotation;
}
