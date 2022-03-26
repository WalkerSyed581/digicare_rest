package com.digicare.digicare_rest_test.exception;

public class SensorNotFoundException extends RuntimeException {

  public SensorNotFoundException(Object id) {
    super("Could not find sensor " + id);
  }
}
