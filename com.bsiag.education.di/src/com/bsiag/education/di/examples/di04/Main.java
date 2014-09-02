
package com.bsiag.education.di.examples.di04;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;

/**
 * use @Inject on the constructor.
 */
public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new FoodModule());
		injector.getInstance(IFoodService.class).printOffer();
	}
	
	public static class FoodModule implements Module{
		@Override
		public void configure(Binder binder) {
			// bind the food service
			binder.bind(IFoodService.class).to(PizzaSerice.class);
			// bind the print service
			binder.bind(IPrintSerice.class).to(ConsolePrintService.class);
		}
	}

	
	
	public static interface IFoodService{
		void printOffer();
	}
	
	@Singleton
	public static class PizzaSerice implements IFoodService{
		
		private final IPrintSerice printService;
		
		/**
		 * reduce scope to private to ensure only guice is creating new instances 
		 */
		@Inject
		private PizzaSerice(IPrintSerice printSerice) {
			this.printService = printSerice;
			System.out.println("construct pizza service");
		}
		
		@Override
		public void printOffer() {
			printService.print("Some pizzas");
		}
	}
	
	public static interface IPrintSerice{
		void print(String message);
	}
	
	@Singleton
	public static class ConsolePrintService implements IPrintSerice{
		
		/**
		 * reduce scope to private to ensure only guice is creating new instances 
		 */
		@Inject
		private ConsolePrintService(){
			System.out.println("print service");
		}
		@Override
		public void print(String message){
			System.out.println(message);
		}
	}
	
}
