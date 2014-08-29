/**
 *
 */
package com.bsiag.education.di.server.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.AbstractService;

import com.bsiag.education.di.server.model.Person;
import com.bsiag.education.di.server.services.PersonService;
import com.bsiag.education.di.shared.services.DesktopFormData;
import com.bsiag.education.di.shared.services.IDesktopService;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author aho
 */
@Singleton
public class DesktopService extends AbstractService implements IDesktopService {

  private final PersonService m_personService;

  @Inject
  private DesktopService(PersonService personService) {
    m_personService = personService;
  }

  @Override
  public DesktopFormData load(DesktopFormData formData) throws ProcessingException {
    formData.getName().setValue("Hugo");
    for (Person p : m_personService.getPersons()) {
      formData.getPersonTable().addRow(new Object[]{p.getId(), p.getFirstName(), p.getLastName()});
    }
    return formData;
  }
}
