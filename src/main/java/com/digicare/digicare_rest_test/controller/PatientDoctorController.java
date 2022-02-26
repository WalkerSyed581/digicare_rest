package com.digicare.digicare_rest_test.controller;

import java.util.List;

import com.digicare.digicare_rest_test.exception.PermissionNotFoundException;
import com.digicare.digicare_rest_test.exception.UserNotFoundException;
import com.digicare.digicare_rest_test.model.user.Patient;
import com.digicare.digicare_rest_test.model.user.PatientDoctorKey;
import com.digicare.digicare_rest_test.repository.PatientDoctorRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicare.digicare_rest_test.assembler.PatientDoctorModelAssembler;
import com.digicare.digicare_rest_test.exception.ReadingNotFoundException;
import com.digicare.digicare_rest_test.model.user.PatientDoctor;

import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class PatientDoctorController {
    private final PatientDoctorRepository repository;

    private final PatientDoctorModelAssembler assembler;

    PatientDoctorController(PatientDoctorRepository repository, PatientDoctorModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/permission")
    public CollectionModel<EntityModel<PatientDoctor>> all() {
        List<EntityModel<PatientDoctor>> patientDoctors = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(patientDoctors, linkTo(methodOn(SensorPatientDataController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/permission")
    public PatientDoctor newSensorPatientData(@RequestBody PatientDoctor newReading) {
        return repository.save(newReading);
    }

    // Single item

    //@GetMapping("/readings/{patient_id}/{sensor_id}/{timestamp}")
    @GetMapping("/permission/{patient_id}/{doctor_id}")
    public EntityModel<PatientDoctor> one(@PathVariable Long patient_id, @PathVariable Long doctor_id) {
        PatientDoctorKey id = new PatientDoctorKey(patient_id,doctor_id);
        PatientDoctor patientDoctor = repository.findById(id) //
                .orElseThrow(() -> new PermissionNotFoundException(patient_id,doctor_id));
        System.out.println(patientDoctor);
        return assembler.toModel(patientDoctor);

    }

    @GetMapping("/permission/patient/{patient_id}")
    public CollectionModel<EntityModel<PatientDoctor>> permissionByPatient(@PathVariable Long patient_id) {
        List<EntityModel<PatientDoctor>> PatientDoctors = repository.findByPatientId(patient_id).stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        System.out.println(PatientDoctors);

        return CollectionModel.of(PatientDoctors, linkTo(methodOn(SensorPatientDataController.class).all()).withSelfRel());
    }

//  @PutMapping("/readings/{patient_id}/{sensor_id}/{timestamp}")
//  public SensorPatientData replaceEmployee(@RequestBody SensorPatientData newSensorPatientData,@PathVariable Long patient_id,@PathVariable Long sensor_id,@PathVariable Date timestamp) {
//  	SensorPatientDataId id = new SensorPatientDataId(patient_id,sensor_id,timestamp);
//    return repository.findById(id)
//      .map(SensorPatientData -> {<>
//        SensorPatientData.setName(newSensorPatientData.getName());
//        return repository.save(SensorPatientData);
//      })
//      .orElseGet(() -> {
//    	  newSensorPatientData.setId(id);
//    	  return repository.save(newSensorPatientData);
//      });
//  }

    @DeleteMapping("/permission/{patient_id}/{doctor_id}/")
    void deleteEmployee(@PathVariable Long patient_id,@PathVariable Long doctor_id) {
        PatientDoctorKey id = new PatientDoctorKey(patient_id,doctor_id);
        repository.deleteById(id);
    }
}
