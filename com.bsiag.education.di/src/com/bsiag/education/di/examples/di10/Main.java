package com.bsiag.education.di.examples.di10;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.util.Modules;

/**
 * Override service implementations
 */
public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(Modules.override(new FoodModule()).with(new AbstractModule() {
			
			@Override
			protected void configure() {
				bind(IFoodService.class).to(KebabSerice.class);
			}
		}));

		IFoodService foodService = injector.getInstance(IFoodService.class);
		foodService.printOffer();
	}

	public static class FoodModule implements Module {
		@Override
		public void configure(Binder binder) {
			// bind the food service
			binder.bind(IFoodService.class).to(PizzaService.class);
		}
	}

	public static interface IFoodService {
		void printOffer();
	}

	@Singleton
	public static class PizzaService implements IFoodService {

		@Inject
		private PizzaService() {
		}

		@Override
		public void printOffer() {
			System.out.println("Some pizzas");
		}
	}

	@Singleton
	public static class KebabSerice implements IFoodService {

		@Inject
		private KebabSerice() {
		}

		@Override
		public void printOffer() {
			System.out.println("Bizeli schaf kebab");
		}
	}

}
