package com.bsiag.education.di.exercises.ex01;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO Change to dependency injection
 *
 */
public class Main {

	public static void main(String[] args) {
		ILogSerice logService = new ConsoleLogService();
		logService.setLogPrefix("LOG: ");
		logService.setPrintStream(System.err);
		IPersonService personService = new PersonService(logService);
		personService.addPerson(new Person(logService).setName("Meier").setPrename("Hans"));
		personService.addPerson(new Person(logService).setName("Eddison").setPrename("Robert"));
		
		//
		for(Person p : personService.getAllPersons()){
			p.logFullName();
		}
		
	}

	public static interface IPersonService{
		void addPerson(Person p);
		List<Person> getAllPersons();
	}
	
	public static class PersonService implements IPersonService{
		private List<Person> persons = new ArrayList<Main.Person>();
		private ILogSerice logService;
		
		public PersonService(ILogSerice logService){
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
	
	public static class ConsoleLogService implements ILogSerice{
		private PrintStream printStream = System.out;
		private String logPrefix;
		
		@Override
		public void setPrintStream(PrintStream printStream) {
			this.printStream = printStream;
		}
		
		@Override
		public void setLogPrefix(String logPrefix) {
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
		
		public Person(ILogSerice logService){
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
