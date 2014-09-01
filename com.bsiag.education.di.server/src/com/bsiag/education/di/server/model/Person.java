package com.bsiag.education.di.server.model;

public class Person {

  private final long m_id;
  private String m_name;
  private String m_firstName;

  public Person(long id, String firstName, String lastName) {
    m_id = id;
    m_firstName = firstName;
    m_name = lastName;
  }

  public long getId() {
    return m_id;
  }

  public String getName() {
    return m_name;
  }

  public void setName(String name) {
    this.m_name = name;
  }

  public String getFirstName() {
    return m_firstName;
  }

  public void setFirstName(String firstName) {
    this.m_firstName = firstName;
  }

}
