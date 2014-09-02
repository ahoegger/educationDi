package com.bsiag.education.di.examples.di17;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * an example for ranked services.
 */
public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new Module(){
			@Override
			public void configure(Binder binder) {
				binder.bind(IPersonService.class).toProvider(PersonServiceProvider.class);
			}
		});

		// propagate service
		PersonServiceProvider personServiceProvider = injector.getInstance(PersonServiceProvider.class);
		personServiceProvider.add(injector.getInstance(PersonService.class));
		personServiceProvider.add(injector.getInstance(PersonServiceEx.class));
		
		System.out.println(personServiceProvider.getOrderedServices().size());
		System.out.println(injector.getInstance(IPersonService.class).toString());
	}

	
}
