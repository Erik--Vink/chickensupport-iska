package com.infosupport.iska.config;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Startup
@Singleton
public class ResourceBinder {

	private final JavaEEConfig config = new JavaEEConfig();

	@PostConstruct
	public void bindResources() {

		try {

			Context context = new InitialContext();

			context.rebind("CreateChicken", config.createChicken());
			context.rebind("FindChicken", config.findChicken());
			context.rebind("LayEggsForChicken", config.layEggsForChicken());

			System.out.println("Resource bound...");

			System.out.println(" " + context.lookup("CreateChicken"));

		} catch (NamingException ex) {

			throw new IllegalStateException("Cannot bind resource " + ex, ex);

		}

	}

}
