package com.bsiag.education.di.examples.di14;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PersonServiceEx extends PersonService {

	@Inject
	protected PersonServiceEx() {
	}

	@Override
	public int getRanking() {
		return 1;
	}

	public String getSkinColor() {
		return "yellow";
	}

	@Override
	public String toString() {
		return super.toString() + " has " + getSkinColor() + " skin.";
	}

}
