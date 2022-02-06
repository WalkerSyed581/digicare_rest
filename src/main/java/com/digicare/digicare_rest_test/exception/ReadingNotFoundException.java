package com.digicare.digicare_rest_test.exception;



public class ReadingNotFoundException extends RuntimeException {

  public ReadingNotFoundException(Long id) {
    super("Could not find reading with id: " + id);
  }
}
