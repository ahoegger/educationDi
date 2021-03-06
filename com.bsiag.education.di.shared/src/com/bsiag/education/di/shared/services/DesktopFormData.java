/**
 * 
 */
package com.bsiag.education.di.shared.services;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.tablefield.AbstractTableFieldData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 * 
 * @generated
 */
@Generated(value = "org.eclipse.scout.sdk.workspace.dto.formdata.FormDataDtoUpdateOperation", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class DesktopFormData extends AbstractFormData {

  private static final long serialVersionUID = 1L;

  public DesktopFormData() {
  }

  public PersonTable getPersonTable() {
    return getFieldByClass(PersonTable.class);
  }

  public static class PersonTable extends AbstractTableFieldData {

    private static final long serialVersionUID = 1L;
    public static final int ID_COLUMN_ID = 0;
    public static final int FIRSTNAME_COLUMN_ID = 1;
    public static final int NAME_COLUMN_ID = 2;

    public PersonTable() {
    }

    public String getFirstname(int row) {
      return (String) getValueInternal(row, FIRSTNAME_COLUMN_ID);
    }

    public void setFirstname(int row, String firstname) {
      setValueInternal(row, FIRSTNAME_COLUMN_ID, firstname);
    }

    public Long getId(int row) {
      return (Long) getValueInternal(row, ID_COLUMN_ID);
    }

    public void setId(int row, Long id) {
      setValueInternal(row, ID_COLUMN_ID, id);
    }

    public String getName(int row) {
      return (String) getValueInternal(row, NAME_COLUMN_ID);
    }

    public void setName(int row, String name) {
      setValueInternal(row, NAME_COLUMN_ID, name);
    }

    @Override
    public int getColumnCount() {
      return 3;
    }

    @Override
    public Object getValueAt(int row, int column) {
      switch (column) {
        case ID_COLUMN_ID:
          return getId(row);
        case FIRSTNAME_COLUMN_ID:
          return getFirstname(row);
        case NAME_COLUMN_ID:
          return getName(row);
        default:
          return null;
      }
    }

    @Override
    public void setValueAt(int row, int column, Object value) {
      switch (column) {
        case ID_COLUMN_ID:
          setId(row, (Long) value);
          break;
        case FIRSTNAME_COLUMN_ID:
          setFirstname(row, (String) value);
          break;
        case NAME_COLUMN_ID:
          setName(row, (String) value);
          break;
      }
    }
  }
}
