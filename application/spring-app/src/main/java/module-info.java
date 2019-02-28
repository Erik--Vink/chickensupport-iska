module com.infosupport.iska.spring {
	requires com.infosupport.iska.config;
	requires com.infosupport.iska.usecase;
	requires slalom.example.controller.spring;

	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;

	exports com.infosupport.iska.spring.config;
	exports com.infosupport.iska.spring;
	opens com.infosupport.iska.spring.config to spring.core;
	opens com.infosupport.iska.spring to spring.core;
}
