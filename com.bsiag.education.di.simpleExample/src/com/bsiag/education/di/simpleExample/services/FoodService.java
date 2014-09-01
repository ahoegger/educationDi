package com.bsiag.education.di.simpleExample.services;

import com.bsiag.education.di.simple.Inject;

public class FoodService implements IFoodService {

	private IPizzaService pizzaService;

	@Inject
	public FoodService(IPizzaService pizzaService){
		this.pizzaService = pizzaService;
	}

	@Override
	public IPizzaService getPizzaService() {
		return pizzaService;
	}
}
