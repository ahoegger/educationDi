/**
 *
 */
package com.bsiag.education.di.server.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.TypeCastUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.server.services.common.jdbc.ISqlService;

import com.bsiag.education.di.server.model.Person;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author aho
 */
@Singleton
public class PersonService {
  private final ISqlService m_sqlService;

  @Inject
  private PersonService(ISqlService sqlService) {
    m_sqlService = sqlService;
    System.out.println("NEW PERSON SERVICE INST");

  }

  public List<Person> getPersons() throws ProcessingException {
    List<Person> persons = new ArrayList<Person>();
    Object[][] data = m_sqlService.select("SELECT ID, PRENAME, NAME FROM PERSON");
    for (Object[] personData : data) {
      Person p = new Person(TypeCastUtility.castValue(personData[0], Integer.class).intValue(),
          TypeCastUtility.castValue(personData[1], String.class),
          TypeCastUtility.castValue(personData[2], String.class));
      persons.add(p);
    }
    return persons;
  }
}
