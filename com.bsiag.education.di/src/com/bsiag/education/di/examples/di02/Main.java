
package com.bsiag.education.di.examples.di02;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * Binding of an interface to a class
 */
public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new FoodModule());
		
		injector.getInstance(IFoodService.class).printOffer();
		injector.getInstance(IFoodService.class).printOffer();
	}
	
	public static class FoodModule implements Module{
		@Override
		public void configure(Binder binder) {
			binder.bind(IFoodService.class).to(PizzaSerice.class);
		}
	}

	
	
	public static interface IFoodService{
		void printOffer();
	}
	
	public static class PizzaSerice implements IFoodService{
		private static int instanceCount = 0;
		
		public PizzaSerice() {
			instanceCount++;
		}
		@Override
		public void printOffer() {
			System.out.println("Some pizzas [instance "+instanceCount+"]");
		}
	}
	
}
