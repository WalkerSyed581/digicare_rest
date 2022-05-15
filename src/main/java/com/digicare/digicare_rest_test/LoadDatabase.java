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
import com.digicare.digicare_rest_test.model.user.Gender;
import com.digicare.digicare_rest_test.model.user.PatientDoctorKey;
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
		  AssessmentRepository assessment_repository,
		  SensorRepository sensor_repository,
		  SensorPatientRepository reading_repository,
		  PatientDoctorRepository permission_repository) {
	  
	Date dob_patient1 = DateUtils.calendarFor(1997, Calendar.APRIL, 11).getTime();
	Date dob_patient2 = DateUtils.calendarFor(1995, Calendar.APRIL, 11).getTime();
	
	
	Date dob_doctor1 = DateUtils.calendarFor(1965, Calendar.JUNE, 22).getTime();

	Date dob_cg1 = DateUtils.calendarFor(1965, Calendar.JUNE, 22).getTime();
	Date dob_cg2 = DateUtils.calendarFor(1965, Calendar.JUNE, 22).getTime();

	Date dob_ad1 = DateUtils.calendarFor(1995, Calendar.MAY, 2).getTime();



  
	Role patient_role = new Role(RoleName.valueOf("ROLE_PATIENT"));
	Role caregiver_role = new Role(RoleName.valueOf("ROLE_CG"));
	Role doctor_role = new Role(RoleName.valueOf("ROLE_DOCTOR"));
	Role admin_role = new Role(RoleName.valueOf("ROLE_ADMIN"));

	
	role_repository.save(patient_role);
	role_repository.save(caregiver_role);
	role_repository.save(doctor_role);
	role_repository.save(admin_role);

	
	Address add_1 = new Address("Street 18","House 1","Karachi","21028");
	Address add_2 = new Address("Street 35","House 34","Islamabad","44000");
	Address add_3 = new Address("Street 12","House 31","Lahore","13021");
	Address add_4 = new Address("Street 1","House 10","Karachi","90132");
	Address add_5 = new Address("Street 20","House 2","Karachi","10334");
	Address add_6 = new Address("Street 1","House 210","Fasialabad","10214");

	address_repository.save(add_1);
	address_repository.save(add_2);
	address_repository.save(add_3);
	address_repository.save(add_4);
	address_repository.save(add_5);
	address_repository.save(add_6);


	
	
	
	User user_6 = new User("Abdullah","Chaudhary",false,"achau@gmail.com","abcd1234", "55863696091",
			dob_ad1,Gender.valueOf("MALE"),"1210114900231", 24,"55863696099", List.of(admin_role),add_6);
	
	User user1 = new User("Bilal","Hakim",false,"bilalHakim@gmail.com","abcd1234", "55863696091",
			dob_patient1,Gender.valueOf("MALE"),"1210114900231", 24,"55863696099", List.of(patient_role),add_1);
	
	User user2 = new User("Adnan","Karim",false,"adnanKarim@gmail.com","abcd1234", "55863696091",
	
			dob_patient2,Gender.valueOf("MALE"),"1210114900231", 24,"55863696081", List.of(patient_role),add_2);
	
	User user3 = new User("Afzal","Hakim",false,"afzalhakim@gmail.com","WAkbP","23411092372",dob_doctor1,Gender.valueOf("MALE"),
			"1230129803421",39,"55863696199",List.of(doctor_role),add_3);
	

	user_repository.save(user1);
	user_repository.save(user2);
	
	
	User user4 = new User("Adnan","Habib",false,"adnanhabib@seecs.edu.pk","GnRQS","91175456598",dob_cg1,Gender.valueOf("MALE"),
			"1230129803428",75,"55863696109",List.of(caregiver_role),add_4,user1,"Brother");
	
	User user5 = new User("Afzal","Habib",false,"afzalhabib@gmail.com","RH25x","55763696090",dob_cg2,Gender.valueOf("MALE"),
			"1230129803429",66,"55863697109",List.of(caregiver_role),add_5,user2,"Father");
	

	
	user_repository.save(user3);
	
	user_repository.save(user4);
			
	user_repository.save(user5);

	
	
	Sensor sensor1 = new Sensor("max30102","Pulse Oximeter");
	Sensor sensor2 = new Sensor("mpu3040","Heart Rate Sensor");
	Sensor sensor3 = new Sensor("ir1353","ECG Sensor");
	
	SensorPatientData reading1 = new SensorPatientData(new Date(),71.47,user1,sensor1);
  	SensorPatientData reading2 = new SensorPatientData(new Date(),37.47,user2,sensor2);
 	 SensorPatientData reading3 = new SensorPatientData(new Date(),99.00,user1,sensor1);
	
	com.digicare.digicare_rest_test.model.user.PatientDoctor permission1 = new com.digicare.digicare_rest_test.model.user.PatientDoctor(new PatientDoctorKey(user1.getId(),user3.getId()),user1,user3);
	com.digicare.digicare_rest_test.model.user.PatientDoctor permission2 = new com.digicare.digicare_rest_test.model.user.PatientDoctor(new PatientDoctorKey(user2.getId(),user3.getId()),user2,user3);
	

    return args -> {
		log.info("Preloading " + user1);
		
		log.info("Preloading " + user2);
		
		log.info("Preloading " + user3);
	
		log.info("Preloading " + user4);
				
		log.info("Preloading " + user5);
		log.info("Preloading " + user_6);
		
		log.info("Preloading " + sensor_repository.save(sensor1));
		
		log.info("Preloading " + sensor_repository.save(sensor2));
		
		log.info("Preloading " + sensor_repository.save(sensor3));
		
		log.info("Preloading " + reading_repository.save(reading1));
				
		log.info("Preloading " + reading_repository.save(reading2));
		
		log.info("Preloading " + reading_repository.save(reading3));
		
		log.info("Preloading " + permission_repository.save(permission1));

		log.info("Preloading " + permission_repository.save(permission2));
    };
  }
}
