
package com.bsiag.education.di.examples.di11;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * use providers to customize services.
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
			binder.bind(String.class).annotatedWith(Names.named("pizzaiolo")).toInstance("Alberto");
			binder.bind(IFoodService.class).toProvider(FoodServiceProvider.class);
		}
	}

	@Singleton
	public static class FoodServiceProvider implements Provider<IFoodService>{
		private String pizzaiolo;
		
		@Inject
		private FoodServiceProvider(@Named("pizzaiolo")String pizzaiolo) {
			this.pizzaiolo = pizzaiolo;
		}
		@Override
		public IFoodService get() {
			PizzaSerice foodService = new PizzaSerice();
			foodService.setPizzaioloName(pizzaiolo);
			return foodService;
		}
	}
	
	
	public static interface IFoodService{
		void printOffer();
	}
	
	public static class PizzaSerice implements IFoodService{
		
		private String pizzaiolo;
		PizzaSerice() {
		}
		
		public void setPizzaioloName(String pizzaiolo){
			this.pizzaiolo = pizzaiolo;
			
		}
		@Override
		public void printOffer() {
			System.out.println("Some pizzas by " + pizzaiolo);
		}
	}
	
}
