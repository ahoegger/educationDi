package com.bsiag.education.di.examples.di11;

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
			PizzaSerice pizzaSerice = new PizzaSerice();
			pizzaSerice.setPizzaioloName("Alberto");
			return pizzaSerice;
		}
	}

	public static interface IFoodService {
		void printOffer();
	}

	public static class PizzaSerice implements IFoodService {

		private String pizzaiolo;

		PizzaSerice() {
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
