package com.bsiag.education.di.examples.di02;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * 
 * <h4>Linked bindings</h4>
 * 
 * A linked binding is an interface getting bound to an implementation class.
 * <code>Binder.bind(Interface.class).to(Implementation.class)</code>. The
 * implementation needs to have an empty constructor. Bound implementation
 * classes are created on first use.<p>
 * 
 * With every {@link Injector#getInstance(Class)} call a new instance of the
 * implementation will be returned. The created instances are not kept by the
 * repository and will be garbage collected after reference release.<p>
 * 
 * Linked bindings are the common and to prefer way working with DI.
 */
public class LinkedBinding {

	public static void main(String[] args) {
		// create an injector with a module
		Injector injector = Guice.createInjector(new FoodModule());

		injector.getInstance(IFoodService.class).printOffer();
		injector.getInstance(IFoodService.class).printOffer();
	}

	/**
	 * The module responsible for the bindings.
	 */
	public static class FoodModule implements Module {
		@Override
		public void configure(Binder binder) {
			binder.bind(IFoodService.class).to(PizzaService.class);
		}
	}

	public static interface IFoodService {
		void printOffer();
	}

	public static class PizzaService implements IFoodService {
		private static int instanceCount = 0;

		public PizzaService() {
			instanceCount++;
		}

		@Override
		public void printOffer() {
			System.out.println("Some pizzas [instance " + instanceCount + "]");
		}
	}

}
