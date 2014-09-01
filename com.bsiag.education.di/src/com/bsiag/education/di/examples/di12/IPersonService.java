package com.bsiag.education.di.examples.di12;

import java.util.List;

public interface IPersonService {

	List<Person> getPersons();
	
	void call(Person p);
}
