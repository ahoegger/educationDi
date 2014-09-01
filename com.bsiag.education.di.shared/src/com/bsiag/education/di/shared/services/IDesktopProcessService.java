/**
 * 
 */
package com.bsiag.education.di.shared.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

/**
 * @author aho
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IDesktopProcessService extends IService {

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  DesktopFormData load(DesktopFormData formData) throws ProcessingException;
}
