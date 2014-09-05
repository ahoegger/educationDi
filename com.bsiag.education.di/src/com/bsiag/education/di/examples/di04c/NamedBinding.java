package com.bsiag.education.di.examples.di04c;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * <h4>Named bindings</h4>
 * 
 * When a class interface binding does not fit the granularity e.g. for multiple
 * bindings to the same interface/class named bindings are used. See the
 * pizzaiolo binding and its usage in the constructor of {@link PizzaService}.
 * <p>
 * 
 * <b>Usage:</b><br>
 * Named bindings are used for config properties like resource locations. It's
 * good practice to use named bindings if you are not the owner of the binding
 * key (in our example {@link String}.class).
 * 
 * @author aho
 *
 */
public class NamedBinding {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new FoodModule());

		injector.getInstance(IFoodService.class).printOffer();
	}

	public static class FoodModule implements Module {
		@Override
		public void configure(Binder binder) {
			// bind the food service
			binder.bind(IFoodService.class).to(PizzaService.class);
			// named binding for pizzaiolo
			binder.bind(String.class).annotatedWith(Names.named("pizzaiolo"))
					.toInstance("Antonio");
		}
	}

	public static interface IFoodService {

		void printOffer();

	}

	public static class PizzaService implements IFoodService {
		private String pizzaiolo;

		@Inject
		protected PizzaService(@Named("pizzaiolo") String pizzaiolo) {
			this.pizzaiolo = pizzaiolo;
		}

		@Override
		public void printOffer() {
			System.out.println("Some pizzas by " + pizzaiolo);
		}
	}

}
