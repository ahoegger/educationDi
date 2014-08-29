/**
 *
 */
package com.bsiag.education.di.client.ui.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;

import com.bsiag.education.di.client.services.ILogService;
import com.bsiag.education.di.client.ui.forms.DesktopForm.MainBox.NameField;
import com.bsiag.education.di.client.ui.forms.DesktopForm.MainBox.PersonTableField;
import com.bsiag.education.di.shared.Icons;
import com.bsiag.education.di.shared.services.DesktopFormData;
import com.bsiag.education.di.shared.services.IDesktopService;
import com.google.inject.Inject;

/**
 * @author aho
 */
@FormData(value = DesktopFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class DesktopForm extends AbstractForm {

  private final ILogService m_logService;
  private final IDesktopService m_desktopService;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  @Inject
  private DesktopForm(ILogService logService, IDesktopService desktopService) throws ProcessingException {
    super();
    m_logService = logService;
    m_desktopService = desktopService;
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_CENTER;
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.EclipseScout;
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startView() throws ProcessingException {
    m_logService.log("start desktop form");
    startInternal(new ViewHandler());
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
   * @return the PersonTableField
   */
  public PersonTableField getPersonTableField() {
    return getFieldByClass(PersonTableField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Order(10.0)
    public class NameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Name");
      }
    }

    @Order(20.0)
    public class PersonTableField extends AbstractTableField<PersonTableField.Table> {

      @Override
      protected int getConfiguredGridH() {
        return 3;
      }

      @Order(10.0)
      public class Table extends AbstractExtensibleTable {

        /**
         * @return the PrenameColumn
         */
        public PrenameColumn getPrenameColumn() {
          return getColumnSet().getColumnByClass(PrenameColumn.class);
        }

        /**
         * @return the NameColumn
         */
        public NameColumn getNameColumn() {
          return getColumnSet().getColumnByClass(NameColumn.class);
        }

        /**
         * @return the IdColumn
         */
        public IdColumn getIdColumn() {
          return getColumnSet().getColumnByClass(IdColumn.class);
        }

        @Order(10.0)
        public class IdColumn extends AbstractIntegerColumn {

          @Override
          protected boolean getConfiguredDisplayable() {
            return false;
          }

          @Override
          protected boolean getConfiguredPrimaryKey() {
            return true;
          }

          @Override
          protected boolean getConfiguredVisible() {
            return false;
          }
        }

        @Order(20.0)
        public class PrenameColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("Prename");
          }

          @Override
          protected int getConfiguredWidth() {
            return 150;
          }
        }

        @Order(30.0)
        public class NameColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("Name");
          }

          @Override
          protected int getConfiguredWidth() {
            return 150;
          }
        }
      }
    }
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
//      IDesktopService service = SERVICES.getService(IDesktopService.class);
      DesktopFormData formData = new DesktopFormData();
      exportFormData(formData);
      formData = m_desktopService.load(formData);
      importFormData(formData);

    }
  }
}
