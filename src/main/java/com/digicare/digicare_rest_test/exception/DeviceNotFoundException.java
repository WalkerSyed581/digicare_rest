package com.digicare.digicare_rest_test.exception;

import com.digicare.digicare_rest_test.model.user.User;

public class DeviceNotFoundException extends RuntimeException {

  public DeviceNotFoundException(User patient) {
    super("Could not find device of patient: " + patient);
  }
}
