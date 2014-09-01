package com.bsiag.education.di.examples.di09;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;

/**
 * Override service implementations
 */
public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new FoodModule());

		IFoodService foodService = injector.getInstance(IFoodService.class);
		foodService.printOffer();
	}

	public static class FoodModule implements Module {
		@Override
		public void configure(Binder binder) {
			// bind the food service
			binder.bind(IFoodService.class).to(PizzaSerice.class);
		}
	}

	public static interface IFoodService {
		void printOffer();
	}

	@Singleton
	public static class PizzaSerice implements IFoodService {

		@Inject
		private PizzaSerice() {
		}

		@Override
		public void printOffer() {
			System.out.println("Some pizzas");
		}
	}

	

}
