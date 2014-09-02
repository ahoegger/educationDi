package com.bsiag.education.di.examples.di12;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * a testing example
 */
public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new PersonModule(), new CtiModule());

		IPersonService personService = injector.getInstance(IPersonService.class);
		Person person = personService.getPersons().get(0);
		personService.call(person);
		
	}


}
