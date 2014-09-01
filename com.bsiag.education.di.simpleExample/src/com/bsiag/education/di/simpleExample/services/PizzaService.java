package com.bsiag.education.di.simpleExample.services;

public class PizzaService implements IPizzaService{

	@Override
	public void orderPizza(String name, int amount) {
		System.out.println("New pizza order: "+amount+" "+name);
	}
}
