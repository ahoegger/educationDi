package com.bsiag.education.di.exercises.ex01Solution;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * TODO Change to dependency injection
 *
 */
public class Solution {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new Module() {
			
			@Override
			public void configure(Binder binder) {
				binder.bind(String.class).annotatedWith(Names.named("logPrefix")).toInstance("LOG: ");
				binder.bind(PrintStream.class).annotatedWith(Names.named("logStream")).toInstance(System.err);
				binder.bind(ILogSerice.class).to(ConsoleLogService.class);
				binder.bind(IPersonService.class).to(PersonService.class);
			}
		});
		IPersonService personService = injector.getInstance(IPersonService.class);
		personService.addPerson(injector.getInstance(Person.class).setName("Meier").setPrename("Hans"));
		personService.addPerson(injector.getInstance(Person.class).setName("Eddison").setPrename("Robert"));
		
		
		//
		for(Person p : personService.getAllPersons()){
			p.logFullName();
		}
		
	}

	public static interface IPersonService{
		void addPerson(Person p);
		List<Person> getAllPersons();
	}
	
	@Singleton
	public static class PersonService implements IPersonService{
		private List<Person> persons = new ArrayList<Solution.Person>();
		private ILogSerice logService;
		
		@Inject
		private PersonService(ILogSerice logService){
			this.logService = logService;
		}
		
		@Override
		public void addPerson(Person p) {
			logService.log("Add person: '"+p.getFullName()+"'");
			persons.add(p);
			
		}

		@Override
		public List<Person> getAllPersons() {
			logService.log("Get all persons");
			return persons;
		}
	}
	
	public static interface ILogSerice{
		void log(String message);

		void setLogPrefix(String logPrefix);

		void setPrintStream(PrintStream printStream);
	}
	
	@Singleton
	public static class ConsoleLogService implements ILogSerice{
		private PrintStream printStream = System.out;
		private String logPrefix;

		@Inject
		private ConsoleLogService() {
		}
		
		@Override
		@Inject
		public void setPrintStream(@Named("logStream")PrintStream printStream) {
			this.printStream = printStream;
		}
		
		@Override
		@Inject
		public void setLogPrefix(@Named("logPrefix")String logPrefix) {
			this.logPrefix = logPrefix;
		}
		
		@Override
		public void log(String message) {
			if(logPrefix != null){
				printStream.print(logPrefix);
			}
			printStream.println(message);
		}
	}
	
	public  static class Person{
		private String name;
		private String prename;
		private ILogSerice logService;
		
		@Inject
		private Person(ILogSerice logService){
			this.logService = logService;
		}
		
		
		public Person setName(String name) {
			this.name = name;
			return this;
		}
		public Person setPrename(String prename) {
			this.prename = prename;
			return this;
		}
		
		public void logFullName(){
			logService.log(getFullName());
		}
		public String getFullName(){
			return prename+" "+name;
		}
		
	}
}
