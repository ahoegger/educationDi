
package com.bsiag.education.di.examples.di03;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;

/**
 * Create only one (singleton) instance of a service (object)
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
//			binder.bind(IFoodService.class).to(PizzaSerice.class).in(Singleton.class);
		}
	}

	
	
	public static interface IFoodService{
		void printOffer();
	}
	
	@Singleton
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
