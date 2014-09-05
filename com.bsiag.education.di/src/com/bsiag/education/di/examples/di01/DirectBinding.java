package com.bsiag.education.di.examples.di01;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * <h4>Direct bindings</h4>
 * 
 * Every Class having empty constructor can be created with Guice by default
 * (without binding).
 * 
 * 
 */
public class DirectBinding {

	public static void main(String[] args) {
		// setup guice with no module
		Injector injector = Guice.createInjector();

		String aString = injector.getInstance(String.class);
		System.out.println(aString != null);

		injector.getInstance(Car.class).printName();
	}

	public static class Car {

		public void printName() {
			System.out.println("VW T1");
		}
	}
}
