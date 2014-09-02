package com.bsiag.education.di.exercises.ex02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bsiag.education.di.exercises.ex02.framework.DI;
import com.bsiag.education.di.exercises.ex02.framework.Injector;
import com.google.inject.Singleton;

public class TestDi {

	private Injector injector;

	@Before
	public void setup() {
		Map<Class<?>, Class<?>> bindings = new HashMap<Class<?>, Class<?>>();
		bindings.put(IPizzaService.class, PizzaService.class);
		bindings.put(IShoppingCart.class, ShoppingCart.class);
		injector = DI.createInjector(bindings);
	}

	@Test
	public void test() {
		IPizzaService pizzaService = injector.getInstance(IPizzaService.class);
		Assert.assertNotNull(pizzaService);

		IShoppingCart shoppingCart = injector.getInstance(IShoppingCart.class);
		Assert.assertNotNull(shoppingCart);

		// check singleton annotation the two carts should be the same instance
		Assert.assertEquals(shoppingCart,
				injector.getInstance(IShoppingCart.class));

		// order a pizza
		Pizza pizzaNapoli = injector.getInstance(Pizza.class).setName("Napoli");
		pizzaService.orderPizza(pizzaNapoli);
		Assert.assertEquals(1, shoppingCart.getProducts().size());

		// order a second pizza
		Pizza pizzaStromboli = injector.getInstance(Pizza.class).setName(
				"Stromboli");
		pizzaService.orderPizza(pizzaStromboli);
		Assert.assertEquals(2, shoppingCart.getProducts().size());

		Assert.assertNotEquals(
				"Injection getting the same instanace for new pizzas.",
				pizzaNapoli, pizzaStromboli);

	}

	@After
	public void shutdown() {
		injector = null;
	}

	public static interface IPizzaService {

		void orderPizza(Pizza pizza);

	}

	@Singleton
	public static class PizzaService implements IPizzaService {
		private final IShoppingCart cart;

		@Inject
		private PizzaService(IShoppingCart cart) {
			this.cart = cart;
		}

		public void orderPizza(Pizza pizza) {
			getCart().add(pizza);
		}

		protected IShoppingCart getCart() {
			return cart;
		}

	}

	@Singleton
	public static interface IShoppingCart {
		void add(Object product);

		List<Object> getProducts();
	}

	@Singleton
	public static class ShoppingCart implements IShoppingCart {
		private List<Object> products = new ArrayList<Object>();

		@Override
		public void add(Object product) {
			products.add(product);
			System.out.println("added '" + product + "' to cart.");
		}

		public List<Object> getProducts() {
			return products;
		}
	}

	public static class Pizza {
		private String name;

		public Pizza setName(String name) {
			this.name = name;
			return this;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "Pizza: " + getName();
		}
	}
}
