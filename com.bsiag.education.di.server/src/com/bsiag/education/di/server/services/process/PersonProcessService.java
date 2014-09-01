/**
 *
 */
package com.bsiag.education.di.server.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.AbstractService;

import com.bsiag.education.di.server.model.Person;
import com.bsiag.education.di.server.services.PersonService;
import com.bsiag.education.di.shared.services.IPersonProcessService;
import com.bsiag.education.di.shared.services.PersonFormData;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author aho
 */
@Singleton
public class PersonProcessService extends AbstractService implements IPersonProcessService {

  private final PersonService m_personService;

  @Inject
  private PersonProcessService(PersonService personService) {
    m_personService = personService;
  }

  @Override
  public PersonFormData create(PersonFormData formData) throws ProcessingException {
    long personId = m_personService.getNextPersonId();
    m_personService.createPerson(new Person(personId, formData.getPrename().getValue(), formData.getName().getValue()));
    return formData;
  }

  @Override
  public PersonFormData load(PersonFormData formData) throws ProcessingException {
    Person person = m_personService.getPerson(formData.getPersonNr());
    formData.getName().setValue(person.getLastName());
    formData.getPrename().setValue(person.getFirstName());
    return formData;
  }

  @Override
  public PersonFormData prepareCreate(PersonFormData formData) throws ProcessingException {
    //TODO [aho] business logic here.
    return formData;
  }

  @Override
  public PersonFormData store(PersonFormData formData) throws ProcessingException {
    m_personService.updatePerson(new Person(formData.getPersonNr(), formData.getPrename().getValue(), formData.getName().getValue()));
    return formData;
  }
}
