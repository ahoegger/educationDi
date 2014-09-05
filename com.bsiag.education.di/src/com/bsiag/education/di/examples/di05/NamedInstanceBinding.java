package com.bsiag.education.di.examples.di05;

import java.io.PrintStream;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * <h4>Named bindings and instance bindings together</h4>
 * 
 * This example shows the usage of a named binding to the existing instance
 * {@link System#out}. In this case a named binding is used to not block the
 * namespace of {@link PrintStream}.
 * 
 * @author aho
 */
public class NamedInstanceBinding {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new FoodModule());
		injector.getInstance(IFoodService.class).printOffer();
	}

	public static class FoodModule implements Module {
		@Override
		public void configure(Binder binder) {
			// bind the food service
			binder.bind(IFoodService.class).to(PizzaService.class);
			// bind the pizzaiolo named
			binder.bind(String.class).annotatedWith(Names.named("pizzaiolo"))
					.toInstance("Alberto");
			// bind the print stream named to instance since we System.out is a
			// predefined singleton.
			binder.bind(PrintStream.class)
					.annotatedWith(Names.named("printStream"))
					.toInstance(System.out);

		}
	}

	public static interface IFoodService {
		void printOffer();
	}

	@Singleton
	public static class PizzaService implements IFoodService {

		private final String pizzaiolo;
		private final PrintStream out;

		@Inject
		protected PizzaService(@Named("pizzaiolo") String pizzaiolo,
				@Named("printStream") PrintStream out) {
			this.pizzaiolo = pizzaiolo;
			this.out = out;
		}

		@Override
		public void printOffer() {
			out.println("Some pizzas by " + pizzaiolo);
		}
	}

}
