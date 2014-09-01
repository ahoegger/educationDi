package com.bsiag.education.di.examples.di12;

import com.google.inject.Binder;
import com.google.inject.Module;

public class PersonModule implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bind(IPersonService.class).to(PersonService.class);
	}

}
