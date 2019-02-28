module com.infosupport.iska.db.hazelcast {
	exports com.infosupport.iska.db.hazelcast;

	requires com.infosupport.iska.domain;
	requires com.hazelcast.core;
	requires java.se;
	requires java.base;
	requires java.management;
	requires jdk.management;
}
