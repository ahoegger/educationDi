/**
 *
 */
package com.bsiag.education.di.server.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.TypeCastUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.holders.NVPair;
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

  }

  public List<Person> getPersons() throws ProcessingException {
    List<Person> persons = new ArrayList<Person>();
    Object[][] data = m_sqlService.select("SELECT ID, FIRSTNAME, NAME FROM PERSON");
    for (Object[] personData : data) {
      Person p = new Person(TypeCastUtility.castValue(personData[0], Integer.class).intValue(),
          TypeCastUtility.castValue(personData[1], String.class),
          TypeCastUtility.castValue(personData[2], String.class));
      persons.add(p);
    }
    return persons;
  }

  /**
   * @return
   * @throws ProcessingException
   */
  public Long getNextPersonId() throws ProcessingException {
    return m_sqlService.getSequenceNextval("SEQ");
  }

  /**
   * @param person
   * @throws ProcessingException
   */
  public void createPerson(Person person) throws ProcessingException {
    m_sqlService.insert("INSERT INTO PERSON (ID, FIRSTNAME, NAME) VALUES(:ID, :firstname, :NAME)",
        new NVPair("ID", person.getId()), new NVPair("firstname", person.getFirstName()), new NVPair("NAME", person.getName()));
  }

  /**
   * @param personNr
   * @throws ProcessingException
   */
  public Person getPerson(Long personNr) throws ProcessingException {
    Object[][] result = m_sqlService.select("SELECT FIRSTNAME, NAME FROM PERSON WHERE ID=:id", new NVPair("id", personNr));
    if (result.length == 1) {
      return new Person(personNr, TypeCastUtility.castValue(result[0][0], String.class), TypeCastUtility.castValue(result[0][1], String.class));
    }
    return null;
  }

  public void updatePerson(Person person) throws ProcessingException {
    m_sqlService.update("UPDATE PERSON SET FIRSTNAME=:firstname, NAME=:name WHERE id=:id",
        new NVPair("id", person.getId()), new NVPair("firstname", person.getFirstName()), new NVPair("name", person.getName()));
  }

}
