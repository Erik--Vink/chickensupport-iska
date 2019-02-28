module com.infosupport.iska.domain {
	exports com.infosupport.iska.domain.entity;
	exports com.infosupport.iska.domain.exception;
	exports com.infosupport.iska.domain.port;
	opens com.infosupport.iska.domain.entity to com.fasterxml.jackson.databind;
}
