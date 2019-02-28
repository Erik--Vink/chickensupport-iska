module com.infosupport.iska.config {
	exports com.infosupport.iska.config;

	requires com.infosupport.iska.usecase;
	requires com.infosupport.iska.domain;
	requires com.infosupport.iska.jug;
	requires com.infosupport.iska.uuid;
	requires com.infosupport.iska.db.simple;
	requires com.infosupport.iska.db.hazelcast;
	requires com.infosupport.iska.controller.vertx;
}
