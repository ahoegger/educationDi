package com.bsiag.education.di.examples.di06;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * guice is nullsafe
 * optional method bindings
 */
public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new FoodModule());
		injector.getInstance(IFoodService.class).printOffer();
	}

	public static class FoodModule implements Module {
		@Override
		public void configure(Binder binder) {
//			binder.bind(String.class).annotatedWith(Names.named("pizzaiolo")).toInstance("Alberto");
			// bind the food service
			binder.bind(IFoodService.class).to(PizzaSerice.class);
		}
	}

	public static interface IFoodService {
		void printOffer();
	}

	@Singleton
	public static class PizzaSerice implements IFoodService {

		private String pizzaiolo;

		@Inject
		private PizzaSerice( ) {
		}
		
		@Inject(optional=true)
		public void setPizzaiolo( @Named("pizzaiolo")String pizzaiolo) {
			this.pizzaiolo = pizzaiolo;
		}

		@Override
		public void printOffer() {
			System.out.println("Some pizzas by " + pizzaiolo);
		}
	}

}
