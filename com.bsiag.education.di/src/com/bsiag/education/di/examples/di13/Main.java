package com.bsiag.education.di.examples.di13;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * use providers to customize services.
 */
public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new FoodModule());

		injector.getInstance(IFoodService.class).printOffer();
		injector.getInstance(IFoodService.class).printOffer();
	}

	public static class FoodModule implements Module {
		@Override
		public void configure(Binder binder) {
		}

		@Provides
		@Singleton
		public IFoodService provideFoodService() {
			PizzaService PizzaService = new PizzaService();
			PizzaService.setPizzaioloName("Alberto");
			return PizzaService;
		}
	}

	public static interface IFoodService {
		void printOffer();
	}

	public static class PizzaService implements IFoodService {

		private String pizzaiolo;

		PizzaService() {
		}

		public void setPizzaioloName(String pizzaiolo) {
			this.pizzaiolo = pizzaiolo;

		}

		@Override
		public void printOffer() {
			System.out.println("Some pizzas by " + pizzaiolo);
		}
	}

}
