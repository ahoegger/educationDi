package com.bsiag.education.di.examples.di14;

import java.util.List;

public interface IPersonService {

	List<Person> getPersons();
	
	void call(Person p);
}
