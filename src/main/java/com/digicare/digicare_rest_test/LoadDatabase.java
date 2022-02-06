package com.digicare.digicare_rest_test;

import java.util.Date;
import java.util.Calendar;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.digicare.digicare_rest_test.repository.*;
import com.digicare.digicare_rest_test.model.*;
import com.digicare.digicare_rest_test.utils.DateUtils;


@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(
		  PatientRepository patient_repository,
		  DoctorRepository doctor_repository,
		  CaregiverRepository cg_repository,
		  AssessmentRepository assessment_repository,
		  SensorRepository sensor_repository,
		  SensorPatientDataRepository reading_repository) {
	  
	Date dob_patient1 = DateUtils.calendarFor(1997, Calendar.APRIL, 11).getTime();
	Date dob_patient2 = DateUtils.calendarFor(1995, Calendar.APRIL, 11).getTime();
	
	
	Date dob_doctor1 = DateUtils.calendarFor(1965, Calendar.JUNE, 22).getTime();

	Date dob_cg1 = DateUtils.calendarFor(1965, Calendar.JUNE, 22).getTime();
	Date dob_cg2 = DateUtils.calendarFor(1965, Calendar.JUNE, 22).getTime();

   
	
	Patient patient1 = new Patient("Danial", "Aziz", "danaziz@gmail.com","abcd1234", "03045678901",
  		  dob_patient1, Gender.valueOf("MALE"),"address",22,"03045678901");
	
	Patient patient2 = new Patient("Muhammad", "Azam", "danaziz@gmail.com","abcd1234","03045678902",dob_patient2,
  		  Gender.valueOf("MALE"),"address",22,"03045678902");
	
	Doctor doctor1 = new Doctor("Afzal","Hakim","afzalhakim@gmail.com","WAkbP","23411092372",dob_doctor1,Gender.valueOf("MALE"),"House 1, Street 18, Karachi",39,"23411092372");
	
	Caregiver caregiver1 = new Caregiver("Adnan","Habib","adnanhabib@seecs.edu.pk","GnRQS","91175456598",dob_cg1,Gender.valueOf("MALE"),"House 34, Street 37, Islamabad",75,"Father",patient1);
	Caregiver caregiver2 = new Caregiver("Afzal","Habib","afzalhabib@gmail.com","RH25x","55763696090",dob_cg2,Gender.valueOf("MALE"),"House 34, Street 18, Karachi",66,"Brother",patient2);

	Sensor sensor1 = new Sensor("max30102","Pulse Oximeter");
	Sensor sensor2 = new Sensor("mpu3040","Heart Rate Sensor");
	Sensor sensor3 = new Sensor("ir1353","ECG Sensor");
	
	SensorPatientData reading1 = new SensorPatientData(new Date(),71.47,patient1,sensor1);
	SensorPatientData reading2 = new SensorPatientData(new Date(),37.47,patient2,sensor2);
	SensorPatientData reading3 = new SensorPatientData(new Date(),99.00,patient1,sensor1);
	
    return args -> {
		log.info("Preloading " + patient_repository.save(patient1));
		
		log.info("Preloading " + patient_repository.save(patient2));
		
		log.info("Preloading " + doctor_repository.save(doctor1));
		
		log.info("Preloading " + cg_repository.save(caregiver1));
				
		log.info("Preloading " + cg_repository.save(caregiver2));
		
		log.info("Preloading " + sensor_repository.save(sensor1));
		
		log.info("Preloading " + sensor_repository.save(sensor2));
		
		log.info("Preloading " + sensor_repository.save(sensor3));
		
		log.info("Preloading " + reading_repository.save(reading1));
				
		log.info("Preloading " + reading_repository.save(reading2));
		
		log.info("Preloading " + reading_repository.save(reading3));
    };
  }
}
