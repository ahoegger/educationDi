package com.bsiag.education.di.examples.di04b;

import com.bsiag.education.di.examples.di05.NamedInstanceBinding;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * <h4>Instance bindings</h4>
 * 
 * Similar to linked bindings it is also possible to bind an interface to a
 * concrete instance. Instance bindings are per definition singleton bindings.
 * <p>
 * 
 * <b>Usage:</b><br>
 * Instance bindings are often used to bind an existing singleton to a key (see
 * also {@link NamedInstanceBinding}).
 * <p>
 * 
 * <b>Instances of instance bindings are created during bootstrap time of Guice.
 * Aware performance leaks!</b>
 * 
 * @author aho
 *
 */
public class InstanceBinding {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new FoodModule());

		// lookup the service two times to ensure the same instance
		injector.getInstance(IFoodService.class).printOffer();
		injector.getInstance(IFoodService.class).printOffer();

	}

	public static class FoodModule implements Module {
		@Override
		public void configure(Binder binder) {
			// bind the food service
			binder.bind(IFoodService.class).toInstance(new PizzaService());
		}
	}

	public static interface IFoodService {

		void printOffer();

	}

	public static class PizzaService implements IFoodService {

		@Inject
		protected PizzaService(){
		}
		
		@Override
		public void printOffer() {
			System.out.println("Some pizzas [service:'" + this + "']");
		}
	}

}
