package com.bsiag.education.di.examples.di04;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;

/**
 * <h4>Constructor injection</h4>
 * 
 * A constructor of a bound implementation can be marked with the {@link Inject}
 * annotation. This annotation tells guice to use this contstuctor to create the
 * instance. Once a constructor is marked with {@link Inject} the scope of the
 * constructor can be reduces to private or protected. Guice will use
 * {@link Constructor#setAccessible(true)} to create the instance.
 * <p>
 * 
 * The arguments of the constructor will be injected of Guice. So all arguments
 * must be known by Guice as well. If one of the arguments can not be resolved
 * nor instantiated an error will occure.
 * <p>
 * 
 * <b>Good practice:</b><br>
 * It is good practice to create argument void constructors with a reduced scope
 * (protected/private) in all Guice managed implementations. This helps to
 * ensure all instances of this object are provided by Guice.<br>
 * Use protected when anybody could be interested in subclassing this object -
 * mostly used for test cases!
 * 
 * 
 */
public class ConstructorInjection {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new FoodModule());
		injector.getInstance(IFoodService.class).printOffer();
	}

	public static class FoodModule implements Module {
		@Override
		public void configure(Binder binder) {
			// bind the food service
			binder.bind(IFoodService.class).to(PizzaService.class);
			// bind the print service
			binder.bind(IPrintSerice.class).to(ConsolePrintService.class);
		}
	}

	public static interface IFoodService {
		void printOffer();
	}

	@Singleton
	public static class PizzaService implements IFoodService {

		private final IPrintSerice printService;

		/**
		 * reduce scope to protected to ensure only guice is creating new
		 * instances.
		 */
		@Inject
		protected PizzaService(IPrintSerice printSerice) {
			this.printService = printSerice;
			System.out.println("construct pizza service");
		}

		@Override
		public void printOffer() {
			printService.print("Some pizzas");
		}
	}

	public static interface IPrintSerice {
		void print(String message);
	}

	@Singleton
	public static class ConsolePrintService implements IPrintSerice {

		/**
		 * reduce scope to private to ensure only guice is creating new
		 * instances
		 */
		@Inject
		protected ConsolePrintService() {
		}

		@Override
		public void print(String message) {
			System.out.println(message);
		}
	}

}
