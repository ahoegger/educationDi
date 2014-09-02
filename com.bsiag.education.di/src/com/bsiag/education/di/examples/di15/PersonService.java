package com.bsiag.education.di.examples.di15;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PersonService implements IPersonService {

	@Inject
	protected PersonService() {
	}

	@Override
	public int getRanking() {
		return 0;
	}

	@Override
	public String getName() {
		return "Hugo Boss";
	}

	@Override
	public String toString() {
		return getName();
	}

}
