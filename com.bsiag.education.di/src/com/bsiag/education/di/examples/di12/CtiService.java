package com.bsiag.education.di.examples.di12;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CtiService implements ICtiService {

	@Inject
	private CtiService() {
	}
	
	@Override
	public void call(String phonenumber) {
		System.out.println("prod: call number '"+phonenumber+"'");
	}

}
