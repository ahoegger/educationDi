/**
 *
 */
package com.bsiag.education.di.client.ui.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;

import com.bsiag.education.di.client.ui.forms.PersonForm.MainBox.CancelButton;
import com.bsiag.education.di.client.ui.forms.PersonForm.MainBox.FirstnameField;
import com.bsiag.education.di.client.ui.forms.PersonForm.MainBox.NameField;
import com.bsiag.education.di.client.ui.forms.PersonForm.MainBox.OkButton;
import com.bsiag.education.di.shared.services.IPersonProcessService;
import com.bsiag.education.di.shared.services.PersonFormData;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * @author aho
 */
@FormData(value = PersonFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PersonForm extends AbstractForm {

  private Long m_personNr;
  private final IPersonProcessService m_personService;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  @Inject
  public PersonForm(IPersonProcessService personService) throws ProcessingException {
    super();
    m_personService = personService;
  }

  /**
   * @return the PersonNr
   */
  @FormData
  public Long getPersonNr() {
    return m_personNr;
  }

  /**
   * @param personNr
   *          the PersonNr to set
   */
  @FormData
  @Inject(optional = true)
  public void setPersonNr(@Named("PERSON_ID") Long personNr) {
    m_personNr = personNr;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Person");
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  /**
   * @return the CancelButton
   */
  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the NameField
   */
  public NameField getNameField() {
    return getFieldByClass(NameField.class);
  }

  /**
   * @return the OkButton
   */
  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  /**
   * @return the PrenameField
   */
  public FirstnameField getFirstnameField() {
    return getFieldByClass(FirstnameField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class NameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Name");
      }
    }

    @Order(20.0)
    public class FirstnameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Firstname");
      }
    }

    @Order(30.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(40.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = m_personService.load(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = m_personService.store(formData);

    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = m_personService.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = m_personService.create(formData);

    }
  }
}
