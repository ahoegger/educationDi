package com.bsiag.education.di.examples.di01;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Every Class having an argument empty constructor can be created with Guice by default.
 */
public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector();
		
		String aString = injector.getInstance(String.class);
		System.out.println(aString != null);
		
		injector.getInstance(Car.class).printName();
	}

	
	
	public static class Car{
		
		void printName(){
			System.out.println("VW T1");
		}
	}
}
