package com.infosupport.iska.spring.config;

import com.infosupport.iska.config.SpringConfig;
import com.infosupport.iska.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	private final SpringConfig config = new SpringConfig();

	@Bean
	public CreateChicken createChicken() {
		return config.createChicken();
	}

	@Bean
	public FindChicken findChicken() {
		return config.findChicken();
	}

	@Bean
	public LayEggsForChicken layEggsForChicken() {
		return config.layEggsForChicken();
	}
}
