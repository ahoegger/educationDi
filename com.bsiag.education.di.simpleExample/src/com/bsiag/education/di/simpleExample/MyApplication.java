package com.bsiag.education.di.simpleExample;

import java.util.HashMap;
import java.util.Map;

import com.bsiag.education.di.simple.DI;
import com.bsiag.education.di.simple.Injector;
import com.bsiag.education.di.simpleExample.services.FoodService;
import com.bsiag.education.di.simpleExample.services.IFoodService;
import com.bsiag.education.di.simpleExample.services.IPizzaService;
import com.bsiag.education.di.simpleExample.services.PizzaService;

public class MyApplication {

	public static void main(String[] args) {
		// create bindings
		Map<Class<?>, Class<?>> bindings = new HashMap<Class<?>, Class<?>>();
		bindings.put(IPizzaService.class, PizzaService.class);
		bindings.put(IFoodService.class, FoodService.class);
		// create injector
		Injector injector = DI.createInjector(bindings);

		// do it
		IPizzaService pizzaService = injector.createInstance(IPizzaService.class);
		pizzaService.orderPizza("Margarita", 2);

		// service with dependencies
		IFoodService foodService = injector.createInstance(IFoodService.class);
		foodService.getPizzaService().orderPizza("Diavola",5);
	}

}
