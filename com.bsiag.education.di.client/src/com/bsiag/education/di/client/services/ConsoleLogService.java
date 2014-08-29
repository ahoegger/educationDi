/**
 *
 */
package com.bsiag.education.di.client.services;

/**
 * @author aho
 */
public class ConsoleLogService implements ILogService {

  @Override
  public void log(String message) {
    System.out.println("LOG: '" + message + "'");
  }
}
