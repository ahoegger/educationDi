package com.bsiag.education.di.examples.di12;

import com.google.inject.Binder;
import com.google.inject.Module;

public class CtiModule implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bind(ICtiService.class).to(CtiService.class);
	}

}
