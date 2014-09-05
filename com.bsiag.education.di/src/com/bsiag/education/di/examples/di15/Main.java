package com.bsiag.education.di.examples.di15;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * use field injection for larger hierarchies
 */
public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new FoodModule());

		injector.getInstance(IFoodService.class).print();
	}

	public static class FoodModule implements Module {
		@Override
		public void configure(Binder binder) {
			binder.bind(IFoodService.class).to(PizzaService.class);
		}

	}

	public static interface IFoodService {
		void print();
	}
	
	public static abstract class FastFoodService implements IFoodService{
		
//		@Inject
		private FastFoodTaxCalculatorService calcService;

		public FastFoodService(FastFoodTaxCalculatorService calcService){
			this.calcService = calcService;
		}
		
		
		@Override
		public void print() {
			System.out.println("FastFoodTaxCalculatorService "+(calcService ));
		}
		
	}

	public static class PizzaService extends FastFoodService {


//		@Inject
		private PizzaDeliveryService deliveryService;

		@Inject
		private PizzaService(PizzaDeliveryService deliveryService, FastFoodTaxCalculatorService calcService) {
			super(calcService);
			this.deliveryService = deliveryService;
		}

//		@Inject
//		private PizzaService(){
//			
//		}
		
		
		@Override
		public void print() {
			super.print();
			System.out.println("PizzaDeliveryService "+deliveryService);
		}
	}

	
	public static class FastFoodTaxCalculatorService{
		
	}
	
	public static class PizzaDeliveryService{
		
	}
}
