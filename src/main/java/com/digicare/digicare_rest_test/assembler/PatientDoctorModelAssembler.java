package com.digicare.digicare_rest_test.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.digicare.digicare_rest_test.controller.PatientDoctorController;
import com.digicare.digicare_rest_test.model.user.Patient;
import com.digicare.digicare_rest_test.model.user.PatientDoctor;
import com.digicare.digicare_rest_test.model.user.PatientDoctorKey;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public
class PatientDoctorModelAssembler implements RepresentationModelAssembler<PatientDoctor, EntityModel<PatientDoctor>> {

    @Override
    public EntityModel<PatientDoctor> toModel(PatientDoctor patientDoctor) {
        PatientDoctorKey id = patientDoctor.getId();

        return EntityModel.of(patientDoctor, //
                linkTo(methodOn(PatientDoctorController.class).one(id.getPatientId(),id.getDoctorId())).withSelfRel(),
                linkTo(methodOn(PatientDoctorController.class).all()).withRel("sensorPatientData"));
    }
}