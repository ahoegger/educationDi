package com.bsiag.education.di.server.model;

public class Address {

  private final int m_id;
  private String m_street;
  private String m_city;

  public Address(int id, String street, String city) {
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

  public int getId() {
    return m_id;
  }

}
