package com.bsiag.education.di.server.model;

public class Address {

  private final long m_id;
  private String m_street;
  private String m_city;

  public Address(long id, String street, String city) {
    m_id = id;
    m_street = street;
    m_city = city;
  }

  public String getStreet() {
    return m_street;
  }

  public void setStreet(String street) {
    this.m_street = street;
  }

  public String getCity() {
    return m_city;
  }

  public void setCity(String city) {
    this.m_city = city;
  }

  public long getId() {
    return m_id;
  }

}
