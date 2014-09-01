package com.bsiag.education.di.examples.di12;

public class Person {
	private String name,prename, phone;
	
	public Person(String prename,String name, String phone){
		this.prename = prename;
		this.name = name;
		this.phone = phone;
	}
	
	public String getPrename() {
		return prename;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	
	@Override
	public String toString() {
		return "Person ("+prename+", "+name+")";
	}
}
