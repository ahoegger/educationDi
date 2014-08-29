package com.bsiag.education.di.shared.services.common.text;

import org.eclipse.scout.rt.shared.services.common.text.AbstractDynamicNlsDocumentationTextProviderService;

public class DiDocumentationTextProviderService extends AbstractDynamicNlsDocumentationTextProviderService {

  @Override
  protected String getDynamicNlsBaseName() {
    return "resources.docs.Docs";
  }
}
