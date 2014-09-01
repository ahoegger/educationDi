
package com.bsiag.education.di.examples.di10a;

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
			binder.bind(IFoodService.class).to(PizzaSerice.class);
		}
	}

	@Singleton
	public static class LogServiceProvider implements Provider<AnExpensiveLogService>{
		
		@Override
		public AnExpensiveLogService get() {
			return new AnExpensiveLogService();
		}
	}
	
	
	public static interface IFoodService{
		void printOffer();
	}
	
	public static class PizzaSerice implements IFoodService{
		
		private String pizzaiolo;
		private Provider<AnExpensiveLogService> logServiceProvider;
		
		@Inject
		private PizzaSerice(Provider<AnExpensiveLogService> logServiceProvider) {
			this.logServiceProvider = logServiceProvider;
		}
		
		@Inject
		public void setPizzaioloName(@Named("pizzaiolo")String pizzaiolo){
			this.pizzaiolo = pizzaiolo;
			
		}
		@Override
		public void printOffer() {
			logServiceProvider.get().log("Some pizzas by " + pizzaiolo);
		}
	}
	
	public static final class AnExpensiveLogService{
		@Inject
		private AnExpensiveLogService(){
			// do some expensive stuff
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		public void log(String message){
			System.out.println(message);
		}
	}
	
}
