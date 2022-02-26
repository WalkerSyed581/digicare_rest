package com.digicare.digicare_rest_test.exception;



public class PermissionNotFoundException extends RuntimeException {

    public PermissionNotFoundException(Long patient_id,Long doctor_id) {
        super("Could not find permission for doctor id: " + doctor_id + " for patient " + patient_id);
    }
}
