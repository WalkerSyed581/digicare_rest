package com.digicare.digicare_rest_test.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicare.digicare_rest_test.assembler.SensorPatientDataModelAssembler;
import com.digicare.digicare_rest_test.exception.ReadingNotFoundException;
import com.digicare.digicare_rest_test.model.SensorPatientData;
import com.digicare.digicare_rest_test.repository.SensorPatientDataRepository;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class SensorPatientDataController {

  private final SensorPatientDataRepository repository;

  private final SensorPatientDataModelAssembler assembler;

  SensorPatientDataController(SensorPatientDataRepository repository,SensorPatientDataModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/readings")
  public CollectionModel<EntityModel<SensorPatientData>> all() {
    List<EntityModel<SensorPatientData>> SensorPatientDatas = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(SensorPatientDatas, linkTo(methodOn(SensorPatientDataController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/readings")
  public SensorPatientData newSensorPatientData(@RequestBody SensorPatientData newReading) {
    return repository.save(newReading);
  }

  // Single item
  
  // @GetMapping("/readings/{patient_id}/{sensor_id}/{timestamp}")
  @GetMapping("/readings/{id}")
  public EntityModel<SensorPatientData> one(@PathVariable Long id) {
    SensorPatientData SensorPatientData = repository.findById(id) //
        .orElseThrow(() -> new ReadingNotFoundException(id));

    return assembler.toModel(SensorPatientData);
  }	
  
	//@GetMapping("/readings/{patient_id}/{sensor_id}/{timestamp}")
	 @GetMapping("/readings/patient/{patient_id}/{sensor_id}")
	 public CollectionModel<EntityModel<SensorPatientData>> readingByPatient(@PathVariable Long patient_id,@PathVariable Long sensor_id) {
	   List<EntityModel<SensorPatientData>> SensorPatientDatas = repository.findByPatientIdAndSensorId(patient_id,sensor_id).stream() //
		        .map(assembler::toModel) //
		        .collect(Collectors.toList());
	   System.out.println(SensorPatientDatas);
	
	    return CollectionModel.of(SensorPatientDatas, linkTo(methodOn(SensorPatientDataController.class).all()).withSelfRel());
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

  // @DeleteMapping("/readings/{patient_id}/{sensor_id}/{timestamp}")
  @DeleteMapping("/readings/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
