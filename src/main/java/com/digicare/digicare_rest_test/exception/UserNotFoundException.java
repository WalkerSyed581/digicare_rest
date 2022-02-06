package com.digicare.digicare_rest_test.exception;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(Long id) {
    super("Could not find employee " + id);
  }
}
