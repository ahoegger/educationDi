package com.bsiag.education.di.examples.di03;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;

/**
 * <h4>Singleton binding</h4>
 * 
 * There are two ways to bind an interface to a singleton class:
 * <ul>
 * <li>Add the {@link Singleton} annotation to the implementation.</li>
 * <li>Bind the implementation and mark it as singleton
 * <code>binder.bind(IFoodService.class).to(PizzaService.class).in(Singleton.class)</code>
 * . This way is used when the implementation to bind is not editable.
 * </ul>
 * Bound singletons getting created exactly once on fist use. These instances
 * are managed by the repository. So once such a singleton class is created it
 * will never be released (garbage collected) again.
 * <p>
 * 
 * <b>Aware memory leaks.</b>
 *
 * @author aho
 */
public class SingletonBinding {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new FoodModule());

		injector.getInstance(IFoodService.class).printOffer();
		injector.getInstance(IFoodService.class).printOffer();
	}

	public static class FoodModule implements Module {
		@Override
		public void configure(Binder binder) {
			binder.bind(IFoodService.class).to(PizzaService.class);
			// binder.bind(IFoodService.class).to(PizzaService.class).in(Singleton.class);
		}
	}

	public static interface IFoodService {
		void printOffer();
	}

	@Singleton
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
