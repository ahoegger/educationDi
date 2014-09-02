package com.bsiag.education.di.examples.di14;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;


@Singleton
public class PersonService implements IPersonService {

	private ICtiService ctiService;

	@Inject
	private PersonService(ICtiService ctiService) {
		this.ctiService = ctiService;
	}
	@Override
	public List<Person> getPersons() {
		List<Person> list = new ArrayList<Person>(2);
		list.add(new Person("Bernd", "Adams", "555"));
		list.add(new Person("Stalik", "Moran", "777"));
		return list;
	}

	@Override
	public void call(Person p) {
		ctiService.call(p.getPhone());
	}


}
