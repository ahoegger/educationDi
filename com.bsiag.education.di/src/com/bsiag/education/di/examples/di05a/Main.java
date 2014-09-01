package com.bsiag.education.di.examples.di05a;

import java.lang.reflect.Constructor;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * Constructor binding for non modifiable classes.
 */
public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new FoodModule());
		injector.getInstance(IFoodService.class).printOffer();
	}

	public static class FoodModule implements Module {
		@Override
		public void configure(Binder binder) {
			binder.bind(String.class).annotatedWith(Names.named("pizzaiolo"))
					.toInstance("Alberto");
			// bind the food service
			try {
				Constructor<PizzaSerice> constructor = PizzaSerice.class.getConstructor(String.class);
				binder.bind(IFoodService.class).toConstructor(constructor);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	public static interface IFoodService {
		void printOffer();
	}

	@Singleton
	public static class PizzaSerice implements IFoodService {

		private final String pizzaiolo;

		// constructor must be public
		public PizzaSerice(@Named("pizzaiolo") String pizzaiolo) {
			this.pizzaiolo = pizzaiolo;
		}

		@Override
		public void printOffer() {
			System.out.println("Some pizzas by " + pizzaiolo);
		}
	}

}
