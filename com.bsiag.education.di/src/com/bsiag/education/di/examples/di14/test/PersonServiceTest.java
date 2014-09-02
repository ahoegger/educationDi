package com.bsiag.education.di.examples.di14.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bsiag.education.di.examples.di14.CtiModule;
import com.bsiag.education.di.examples.di14.ICtiService;
import com.bsiag.education.di.examples.di14.IPersonService;
import com.bsiag.education.di.examples.di14.Person;
import com.bsiag.education.di.examples.di14.PersonModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.util.Modules;

public class PersonServiceTest {

	private  Injector injector;

    @Before
    public void setUp() throws Exception {
    	
        injector = Guice.createInjector(new PersonModule(), Modules.override(new CtiModule()).with(new  AbstractModule() {

            @Override
            protected void configure() {
                bind(ICtiService.class).to(MockCtiService.class);
            }
        }));
       
    }
    @Test
    public void test() {
    	IPersonService personService = injector.getInstance(IPersonService.class);
    	List<Person> persons = personService.getPersons();
    	Assert.assertEquals(2, persons.size());
    	personService.call(persons.get(0));
    	Assert.assertEquals(1, injector.getInstance(MockCtiService.class).calledNumbers.size());
    	personService.call(persons.get(1));
    	Assert.assertEquals(2, injector.getInstance(MockCtiService.class).calledNumbers.size());
    }

    @After
    public void tearDown() throws Exception {
        injector = null;
    }


    public static class MockCtiService implements ICtiService{
    	private List<String> calledNumbers = new ArrayList<String>();

    	@Inject
    	private MockCtiService() {
		}
    	
    	@Override
    	public void call(String phonenumber) {
    		System.out.println("test: call number '"+phonenumber+"'");
    		calledNumbers.add(phonenumber);
    	}
    }

}
