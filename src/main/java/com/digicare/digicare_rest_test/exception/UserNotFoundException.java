package com.digicare.digicare_rest_test.exception;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(Object id) {
    super("Could not find employee " + id);
  }
}
