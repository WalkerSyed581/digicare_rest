package com.digicare.digicare_rest_test;

import java.util.Date;
import java.util.List;
import java.util.Calendar;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.digicare.digicare_rest_test.repository.*;
import com.digicare.digicare_rest_test.model.*;
import com.digicare.digicare_rest_test.model.role.Role;
import com.digicare.digicare_rest_test.model.role.RoleName;
import com.digicare.digicare_rest_test.model.user.Address;
import com.digicare.digicare_rest_test.model.user.Caregiver;
import com.digicare.digicare_rest_test.model.user.Doctor;
import com.digicare.digicare_rest_test.model.user.Gender;
import com.digicare.digicare_rest_test.model.user.Patient;
import com.digicare.digicare_rest_test.model.user.User;
import com.digicare.digicare_rest_test.utils.DateUtils;


@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(
		  RoleRepository role_repository,
		  AddressRepository address_repository,
		  UserRepository user_repository,
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

  
	Role patient_role = new Role(RoleName.valueOf("ROLE_PATIENT"));
	Role caregiver_role = new Role(RoleName.valueOf("ROLE_CG"));
	Role doctor_role = new Role(RoleName.valueOf("ROLE_DOCTOR"));
	
	role_repository.save(patient_role);
	role_repository.save(caregiver_role);
	role_repository.save(doctor_role);
	
	Address add_1 = new Address("Street 18","House 1","Karachi","21028");
	Address add_2 = new Address("Street 35","House 34","Islamabad","44000");
	Address add_3 = new Address("Street 12","House 31","Lahore","13021");
	Address add_4 = new Address("Street 1","House 10","Karachi","90132");
	Address add_5 = new Address("Street 20","House 2","Karachi","10334");
	
	address_repository.save(add_1);
	address_repository.save(add_2);
	address_repository.save(add_3);
	address_repository.save(add_4);
	address_repository.save(add_5);

	
	User user1 = new User("Bilal","Hakim",false,"bilalHakim@gmail.com","abcd1234", "55863696091",
			dob_patient1,Gender.valueOf("MALE"),"1210114900231", 24, List.of(patient_role),add_1);
	
	User user2 = new User("Adnan","Karim",false,"adnanKarim@gmail.com","abcd1234", "55863696091",
			dob_patient1,Gender.valueOf("MALE"),"1210114900231", 24, List.of(patient_role),add_2);
	
	User user3 = new User("Afzal","Hakim",false,"afzalhakim@gmail.com","WAkbP","23411092372",dob_doctor1,Gender.valueOf("MALE"),
			"1230129803421",39,List.of(doctor_role),add_3);
	
	User user4 = new User("Adnan","Habib",false,"adnanhabib@seecs.edu.pk","GnRQS","91175456598",dob_cg1,Gender.valueOf("MALE"),
			"1230129803428",75,List.of(caregiver_role),add_4);
	
	User user5 = new User("Afzal","Habib",false,"afzalhabib@gmail.com","RH25x","55763696090",dob_cg2,Gender.valueOf("MALE"),
			"1230129803429",66,List.of(caregiver_role),add_5);
	
	
	user_repository.save(user1);
	user_repository.save(user2);
	user_repository.save(user3);
	user_repository.save(user4);
	user_repository.save(user5);
	
	
	Patient patient1 = new Patient(user1,"12345678901");
		
	Patient patient2 = new Patient(user2,"12345678901");
	
	Doctor doctor1 = new Doctor(user3,"01230459291");
	
	Caregiver caregiver1 = new Caregiver(user4,"Father",patient1);
	
	Caregiver caregiver2 = new Caregiver(user5,"Brother",patient2);

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
