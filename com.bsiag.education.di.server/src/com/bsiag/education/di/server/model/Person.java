package com.bsiag.education.di.server.model;

public class Person {

  private final long m_id;
  private String m_lastName;
  private String m_firstName;

  public Person(long id, String firstName, String lastName) {
    m_id = id;
    m_firstName = firstName;
    m_lastName = lastName;
  }

  public long getId() {
    return m_id;
  }

  public String getLastName() {
    return m_lastName;
  }

  public void setLastName(String lastName) {
    this.m_lastName = lastName;
  }

  public String getFirstName() {
    return m_firstName;
  }

  public void setFirstName(String firstName) {
    this.m_firstName = firstName;
  }

}
