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
import com.digicare.digicare_rest_test.model.user.PatientDoctor;
import com.digicare.digicare_rest_test.model.Assessment;
import com.digicare.digicare_rest_test.model.user.User;
import com.digicare.digicare_rest_test.utils.DateUtils;


@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	// @Bean
  	CommandLineRunner initDatabase(
		  RoleRepository role_repository,		    
		  AddressRepository address_repository,
		  UserRepository user_repository,
		  AssessmentRepository assessment_repository,
		  SensorRepository sensor_repository,
		  SensorPatientRepository reading_repository,
		  PatientDoctorRepository permission_repository) {
	  
				// ================ ADDRESS SEEDS ================

				Address add_1 = new Address("Street 8","House 3","Islamabad","44000");
				address_repository.save(add_1);

				Address add_2 = new Address("Street 7","House 4","Islamabad","44000");
				address_repository.save(add_2);

				Address add_3 = new Address("Street 6","House 5","Islamabad","44000");
				address_repository.save(add_3);

				Address add_4 = new Address("Street 5","House 6","Islamabad","44000");
				address_repository.save(add_4);

				Address add_5 = new Address("Street 4","House 7","Islamabad","44000");
				address_repository.save(add_5);

				Address add_6 = new Address("Street 18","House 23","Lahore","40050");
				address_repository.save(add_6);

				Address add_7 = new Address("Street 17","House 24","Lahore","42000");
				address_repository.save(add_7);

				Address add_8 = new Address("Street 16","House 25","Lahore","53700");
				address_repository.save(add_8);

				Address add_9 = new Address("Street 15","House 26","Lahore","40050");
				address_repository.save(add_9);

				Address add_10 = new Address("Street 14","House 27","Lahore","54500");
				address_repository.save(add_10);

				Address add_11 = new Address("Street 13","House 28","Faisalabad","38000");
				address_repository.save(add_11);

				Address add_12 = new Address("Street 12","House 29","Faisalabad","38060");
				address_repository.save(add_12);

				Address add_13 = new Address("Street 11","House 30","Faisalabad","38070");
				address_repository.save(add_13);

				Address add_14 = new Address("Street 10","House 31","Faisalabad","38090");
				address_repository.save(add_14);

				Address add_15 = new Address("Street 9","House 32","Karachi","24700");
				address_repository.save(add_15);

				Address add_16 = new Address("Street 8","House 33","Karachi","71500");
				address_repository.save(add_16);

				// ================ ROLE SEEDS ================

				Role role_1 = new Role(RoleName.valueOf("ROLE_ADMIN"));
				role_repository.save(role_1);

				Role role_2 = new Role(RoleName.valueOf("ROLE_PATIENT"));
				role_repository.save(role_2);

				Role role_3 = new Role(RoleName.valueOf("ROLE_CG"));
				role_repository.save(role_3);

				Role role_4 = new Role(RoleName.valueOf("ROLE_DOCTOR"));
				role_repository.save(role_4);

				// ================ USER SEEDS ================

				Date dob_1 = DateUtils.calendarFor(1974, Calendar.NOVEMBER, 05).getTime();
				User user_1 = new User("Usman","Ashraf",false,"usmanAshraf@gmail.com","TyUFys", "3321624824",
					dob_1,Gender.valueOf("MALE"),"9459255184917", 47,"3554955971", List.of(role_4),add_1);
				user_repository.save(user_1);

				Date dob_2 = DateUtils.calendarFor(1998, Calendar.NOVEMBER, 07).getTime();
				User user_2 = new User("Abdullah","Khan",false,"abdullahKhan@gmail.com","yGMLpy", "3437454416",
					dob_2,Gender.valueOf("MALE"),"5641082939789", 23,"3017166495", List.of(role_4),add_2);
				user_repository.save(user_2);

				Date dob_3 = DateUtils.calendarFor(1996, Calendar.NOVEMBER, 10).getTime();
				User user_3 = new User("Amna","Marwat",false,"amnaMarwat@gmail.com","vnTthb", "3514282974",
					dob_3,Gender.valueOf("FEMALE"),"1399865793161", 25,"3522782231", List.of(role_4),add_3);
				user_repository.save(user_3);

				Date dob_4 = DateUtils.calendarFor(1991, Calendar.NOVEMBER, 14).getTime();
				User user_4 = new User("Hafsa","Israr",false,"hafsaIsrar@gmail.com","RGFxfv", "3352582356",
					dob_4,Gender.valueOf("FEMALE"),"7905181890334", 30,"3117471933", List.of(role_4),add_4);
				user_repository.save(user_4);

				Date dob_5 = DateUtils.calendarFor(1995, Calendar.NOVEMBER, 01).getTime();
				User user_5 = new User("Afzal","Hakim",false,"afzalHakim@gmail.com","WAkbP", "3104196576",
					dob_5,Gender.valueOf("MALE"),"1397324041239", 26,"3664347661", List.of(role_4),add_5);
				user_repository.save(user_5);

				Date dob_6 = DateUtils.calendarFor(1984, Calendar.MAY, 06).getTime();
				User user_6 = new User("Usama","Ahmad",false,"usamaAhmad@gmail.com","RytRrv", "3220683450",
					dob_6,Gender.valueOf("MALE"),"4626751071761", 38,"3115024706", List.of(role_1),add_6);
				user_repository.save(user_6);

				Date dob_7 = DateUtils.calendarFor(2004, Calendar.NOVEMBER, 04).getTime();
				User user_7 = new User("Aftab","Akhtar",false,"aftabAkhtar@gmail.com","SjDgYo", "3165307216",
					dob_7,Gender.valueOf("MALE"),"3297058888914", 17,"3302063894", List.of(role_2),add_7);
				user_repository.save(user_7);

				Date dob_8 = DateUtils.calendarFor(2012, Calendar.NOVEMBER, 07).getTime();
				User user_8 = new User("Maira","Tariq",false,"mairaTariq@gmail.com","xnVYvO", "3873776809",
					dob_8,Gender.valueOf("FEMALE"),"4191046854790", 9,"3091264277", List.of(role_2),add_8);
				user_repository.save(user_8);

				Date dob_9 = DateUtils.calendarFor(2016, Calendar.MARCH, 02).getTime();
				User user_9 = new User("Maryam","Ashraf",false,"maryamAshraf@gmail.com","IhXTLk", "3312518229",
					dob_9,Gender.valueOf("FEMALE"),"2895177815523", 6,"3210556382", List.of(role_2),add_9);
				user_repository.save(user_9);

				Date dob_10 = DateUtils.calendarFor(2004, Calendar.APRIL, 05).getTime();
				User user_10 = new User("Zainab","Chaudhry",false,"zainabChaudhry@gmail.com","lVFSgE", "3768304588",
					dob_10,Gender.valueOf("FEMALE"),"2985179043744", 18,"3883390091", List.of(role_2),add_10);
				user_repository.save(user_10);

				Date dob_11 = DateUtils.calendarFor(2016, Calendar.APRIL, 11).getTime();
				User user_11 = new User("Qasim","Hassan",false,"qasimHassan@gmail.com","rTZOGK", "3320937611",
					dob_11,Gender.valueOf("MALE"),"3094173326132", 6,"3157910962", List.of(role_2),add_11);
				user_repository.save(user_11);

				Date dob_12 = DateUtils.calendarFor(2017, Calendar.JULY, 13).getTime();
				User user_12 = new User("Indira","Gandhi",false,"indiraGandhi@gmail.com","bhudMT", "3124040730",
					dob_12,Gender.valueOf("FEMALE"),"9042650967443", 4,"3485819834", List.of(role_2),add_12);
				user_repository.save(user_12);

				Date dob_13 = DateUtils.calendarFor(2000, Calendar.SEPTEMBER, 05).getTime();
				User user_13 = new User("Hajrah","Ali",false,"hajrahAli@gmail.com","AwLdAH", "3746297905",
					dob_13,Gender.valueOf("FEMALE"),"2512075516680", 21,"3912175866", List.of(role_2),add_13);
				user_repository.save(user_13);

				Date dob_14 = DateUtils.calendarFor(2003, Calendar.APRIL, 18).getTime();
				User user_14 = new User("Faisal","Virk",false,"faisalVirk@gmail.com","cNsZQH", "3966984618",
					dob_14,Gender.valueOf("MALE"),"9567831137146", 19,"3566612361", List.of(role_2),add_14);
				user_repository.save(user_14);

				Date dob_15 = DateUtils.calendarFor(1993, Calendar.OCTOBER, 13).getTime();
				User user_15 = new User("Bilal","Hakim",false,"bilalHakim@gmail.com","abcd1234", "3628297490",
					dob_15,Gender.valueOf("MALE"),"1863575087627", 28,"3119076682", List.of(role_3),add_15,user_13,"Father");
				user_repository.save(user_15);

				Date dob_16 = DateUtils.calendarFor(1979, Calendar.APRIL, 10).getTime();
				User user_16 = new User("Fatima","Khan",false,"fatimaKhan@gmail.com","jITvRa", "3429967003",
					dob_16,Gender.valueOf("FEMALE"),"6697532590165", 43,"3012410993", List.of(role_3),add_16,user_14,"Sister");
				user_repository.save(user_16);

				// ================ SENSOR SEEDS ================

				Sensor sensor_1 = new Sensor("max30102","Pulse Oximeter");
				sensor_repository.save(sensor_1);

				Sensor sensor_2 = new Sensor("mpu3040","Heart Rate Sensor");
				sensor_repository.save(sensor_2);

				Sensor sensor_3 = new Sensor("ir1353","ECG Sensor");
				sensor_repository.save(sensor_3);

				// ================ READING SEEDS ================

				SensorPatientData reading_1 = new SensorPatientData(new Date(),46.39,user_11,sensor_1);
				reading_repository.save(reading_1);

				SensorPatientData reading_2 = new SensorPatientData(new Date(),67.23,user_13,sensor_2);
				reading_repository.save(reading_2);

				SensorPatientData reading_3 = new SensorPatientData(new Date(),66.28,user_16,sensor_1);
				reading_repository.save(reading_3);

				SensorPatientData reading_4 = new SensorPatientData(new Date(),59.41,user_10,sensor_3);
				reading_repository.save(reading_4);

				SensorPatientData reading_5 = new SensorPatientData(new Date(),64.14,user_10,sensor_2);
				reading_repository.save(reading_5);

				SensorPatientData reading_6 = new SensorPatientData(new Date(),94.57,user_11,sensor_3);
				reading_repository.save(reading_6);

				SensorPatientData reading_7 = new SensorPatientData(new Date(),67.34,user_10,sensor_2);
				reading_repository.save(reading_7);

				SensorPatientData reading_8 = new SensorPatientData(new Date(),53.08,user_13,sensor_2);
				reading_repository.save(reading_8);

				SensorPatientData reading_9 = new SensorPatientData(new Date(),46.08,user_15,sensor_3);
				reading_repository.save(reading_9);

				SensorPatientData reading_10 = new SensorPatientData(new Date(),43.9,user_10,sensor_3);
				reading_repository.save(reading_10);

				SensorPatientData reading_11 = new SensorPatientData(new Date(),76.86,user_10,sensor_2);
				reading_repository.save(reading_11);

				SensorPatientData reading_12 = new SensorPatientData(new Date(),98.98,user_9,sensor_3);
				reading_repository.save(reading_12);

				SensorPatientData reading_13 = new SensorPatientData(new Date(),45.39,user_16,sensor_3);
				reading_repository.save(reading_13);

				SensorPatientData reading_14 = new SensorPatientData(new Date(),45.98,user_12,sensor_1);
				reading_repository.save(reading_14);

				SensorPatientData reading_15 = new SensorPatientData(new Date(),78.78,user_15,sensor_2);
				reading_repository.save(reading_15);

				SensorPatientData reading_16 = new SensorPatientData(new Date(),43.14,user_15,sensor_3);
				reading_repository.save(reading_16);

				SensorPatientData reading_17 = new SensorPatientData(new Date(),89.39,user_10,sensor_3);
				reading_repository.save(reading_17);

				SensorPatientData reading_18 = new SensorPatientData(new Date(),98.41,user_10,sensor_1);
				reading_repository.save(reading_18);

				SensorPatientData reading_19 = new SensorPatientData(new Date(),77.53,user_9,sensor_1);
				reading_repository.save(reading_19);

				SensorPatientData reading_20 = new SensorPatientData(new Date(),37.98,user_13,sensor_1);
				reading_repository.save(reading_20);

				SensorPatientData reading_21 = new SensorPatientData(new Date(),55.14,user_15,sensor_3);
				reading_repository.save(reading_21);

				SensorPatientData reading_22 = new SensorPatientData(new Date(),97.98,user_15,sensor_3);
				reading_repository.save(reading_22);

				SensorPatientData reading_23 = new SensorPatientData(new Date(),66.43,user_11,sensor_3);
				reading_repository.save(reading_23);

				SensorPatientData reading_24 = new SensorPatientData(new Date(),84.11,user_14,sensor_3);
				reading_repository.save(reading_24);

				SensorPatientData reading_25 = new SensorPatientData(new Date(),66.46,user_11,sensor_1);
				reading_repository.save(reading_25);

				SensorPatientData reading_26 = new SensorPatientData(new Date(),95.6,user_13,sensor_3);
				reading_repository.save(reading_26);

				SensorPatientData reading_27 = new SensorPatientData(new Date(),53.6,user_9,sensor_1);
				reading_repository.save(reading_27);

				SensorPatientData reading_28 = new SensorPatientData(new Date(),48.23,user_15,sensor_1);
				reading_repository.save(reading_28);

				SensorPatientData reading_29 = new SensorPatientData(new Date(),74.6,user_12,sensor_2);
				reading_repository.save(reading_29);

				SensorPatientData reading_30 = new SensorPatientData(new Date(),93.33,user_14,sensor_2);
				reading_repository.save(reading_30);

				SensorPatientData reading_31 = new SensorPatientData(new Date(),86.72,user_16,sensor_2);
				reading_repository.save(reading_31);

				SensorPatientData reading_32 = new SensorPatientData(new Date(),46.55,user_16,sensor_3);
				reading_repository.save(reading_32);

				SensorPatientData reading_33 = new SensorPatientData(new Date(),93.81,user_15,sensor_2);
				reading_repository.save(reading_33);

				SensorPatientData reading_34 = new SensorPatientData(new Date(),37.62,user_11,sensor_2);
				reading_repository.save(reading_34);

				SensorPatientData reading_35 = new SensorPatientData(new Date(),84.59,user_9,sensor_2);
				reading_repository.save(reading_35);

				SensorPatientData reading_36 = new SensorPatientData(new Date(),35.81,user_13,sensor_1);
				reading_repository.save(reading_36);

				SensorPatientData reading_37 = new SensorPatientData(new Date(),97.61,user_15,sensor_3);
				reading_repository.save(reading_37);

				SensorPatientData reading_38 = new SensorPatientData(new Date(),34.53,user_10,sensor_2);
				reading_repository.save(reading_38);

				SensorPatientData reading_39 = new SensorPatientData(new Date(),93.69,user_10,sensor_2);
				reading_repository.save(reading_39);

				SensorPatientData reading_40 = new SensorPatientData(new Date(),48.51,user_11,sensor_1);
				reading_repository.save(reading_40);

				SensorPatientData reading_41 = new SensorPatientData(new Date(),95.02,user_10,sensor_2);
				reading_repository.save(reading_41);

				SensorPatientData reading_42 = new SensorPatientData(new Date(),93.33,user_16,sensor_1);
				reading_repository.save(reading_42);

				SensorPatientData reading_43 = new SensorPatientData(new Date(),36.12,user_14,sensor_3);
				reading_repository.save(reading_43);

				SensorPatientData reading_44 = new SensorPatientData(new Date(),45.85,user_11,sensor_2);
				reading_repository.save(reading_44);

				SensorPatientData reading_45 = new SensorPatientData(new Date(),64.11,user_15,sensor_2);
				reading_repository.save(reading_45);

				SensorPatientData reading_46 = new SensorPatientData(new Date(),67.26,user_16,sensor_3);
				reading_repository.save(reading_46);

				SensorPatientData reading_47 = new SensorPatientData(new Date(),66.22,user_15,sensor_3);
				reading_repository.save(reading_47);

				SensorPatientData reading_48 = new SensorPatientData(new Date(),87.24,user_11,sensor_3);
				reading_repository.save(reading_48);

				SensorPatientData reading_49 = new SensorPatientData(new Date(),46.02,user_15,sensor_2);
				reading_repository.save(reading_49);

				SensorPatientData reading_50 = new SensorPatientData(new Date(),77.51,user_10,sensor_2);
				reading_repository.save(reading_50);

				// ================ PERMISSION SEEDS ================

				PatientDoctor permission_1 = new PatientDoctor(new PatientDoctorKey(user_9.getId(),user_1.getId()),user_9,user_1);
				permission_repository.save(permission_1);

				PatientDoctor permission_2 = new PatientDoctor(new PatientDoctorKey(user_9.getId(),user_2.getId()),user_9,user_2);
				permission_repository.save(permission_2);

				PatientDoctor permission_3 = new PatientDoctor(new PatientDoctorKey(user_10.getId(),user_3.getId()),user_10,user_3);
				permission_repository.save(permission_3);

				PatientDoctor permission_4 = new PatientDoctor(new PatientDoctorKey(user_10.getId(),user_4.getId()),user_10,user_4);
				permission_repository.save(permission_4);

				PatientDoctor permission_5 = new PatientDoctor(new PatientDoctorKey(user_11.getId(),user_1.getId()),user_11,user_1);
				permission_repository.save(permission_5);

				PatientDoctor permission_6 = new PatientDoctor(new PatientDoctorKey(user_12.getId(),user_5.getId()),user_12,user_5);
				permission_repository.save(permission_6);

				PatientDoctor permission_7 = new PatientDoctor(new PatientDoctorKey(user_13.getId(),user_5.getId()),user_13,user_5);
				permission_repository.save(permission_7);

				PatientDoctor permission_8 = new PatientDoctor(new PatientDoctorKey(user_13.getId(),user_4.getId()),user_13,user_4);
				permission_repository.save(permission_8);

				PatientDoctor permission_9 = new PatientDoctor(new PatientDoctorKey(user_14.getId(),user_3.getId()),user_14,user_3);
				permission_repository.save(permission_9);

				PatientDoctor permission_10 = new PatientDoctor(new PatientDoctorKey(user_15.getId(),user_2.getId()),user_15,user_2);
				permission_repository.save(permission_10);

				PatientDoctor permission_11 = new PatientDoctor(new PatientDoctorKey(user_16.getId(),user_3.getId()),user_16,user_3);
				permission_repository.save(permission_11);

				PatientDoctor permission_12 = new PatientDoctor(new PatientDoctorKey(user_16.getId(),user_1.getId()),user_16,user_1);
				permission_repository.save(permission_12);

				// ================ ASSESSMENT SEEDS ================

				Assessment assessment_1 = new Assessment("If breathing does not remain level, may require ICU support", "Sick", "Take 2 tablets of medicol every day", "Keep under observation at all times",
					"Heart rate monitor indicates severe issue", user_9, user_1,new Date());
				assessment_repository.save(assessment_1);

				Assessment assessment_2 = new Assessment("If breathing does not remain level, may require ICU support", "Sick", "Take 2 tablets of medicol every day", "Keep under observation at all times",
					"Heart rate monitor indicates severe issue", user_9, user_2,new Date());
				assessment_repository.save(assessment_2);

				Assessment assessment_3 = new Assessment("ICU standby no longer required", "Good", "Reduce dosage of medicol to one every two days. Do not carry out strenuous exercise at this time.", "Checkup occasionally, ideally twice a week",
					"Heart rate monitor shows significant increase in aortic strength", user_9, user_1,new Date());
				assessment_repository.save(assessment_3);

				Assessment assessment_4 = new Assessment("may require some support if condition worsens", "Good", "Rest recommended", "Check occasionally",
					"Normal vital signs", user_10, user_3,new Date());
				assessment_repository.save(assessment_4);

				Assessment assessment_5 = new Assessment("If breathing does not remain level, may require hospital visit", "Average", "Healthy diet and bed rest", "Checkup occasionally, ideally twice a week",
					"SPo2 monitor indicates severe issue", user_10, user_3,new Date());
				assessment_repository.save(assessment_5);

				Assessment assessment_6 = new Assessment("If breathing does not remain level, may require ICU support", "sick", "Take 2 tablets of medicol every day", "Checkup occasionally, ideally twice a week",
					"Heart rate monitor indicates severe issue", user_10, user_3,new Date());
				assessment_repository.save(assessment_6);

				Assessment assessment_7 = new Assessment("May require hospitilisation if condition worsens", "Average", "Take 2 tablets of medicol every day and bed rest", "Keep under observation at all times",
					"vital signs normalizing", user_10, user_4,new Date());
				assessment_repository.save(assessment_7);

				Assessment assessment_8 = new Assessment("If breathing does not remain level, may require ICU support", "Sick", "Take 2 tablets of medicol every day", "Keep under observation at all times",
					"Heart rate monitor indicates severe issue", user_11, user_1,new Date());
				assessment_repository.save(assessment_8);

				Assessment assessment_9 = new Assessment("If breathing does not remain level, may require ICU support", "Average", "Take 2 tablets of medicol every day", "Keep under observation at all times",
					"Heart rate monitor indicates severe issue", user_11, user_1,new Date());
				assessment_repository.save(assessment_9);

				Assessment assessment_10 = new Assessment("Bed rest recommended ", "Good", "Healthy diet and bed rest", "Checkup occasionally, ideally twice a week",
					"Normal vital signs", user_12, user_5,new Date());
				assessment_repository.save(assessment_10);

				Assessment assessment_11 = new Assessment("Hospital visit if condition worsens ", "average", "Take care and precautions according to condition", "Keep under observation at all times",
					"Vital signs moderate values", user_12, user_5,new Date());
				assessment_repository.save(assessment_11);

				Assessment assessment_12 = new Assessment("If breathing does not remain level, may require ICU support", "Average", "Rest recommended", "Check occasionally",
					"Heart rate monitor indicates severe issue", user_13, user_5,new Date());
				assessment_repository.save(assessment_12);

				Assessment assessment_13 = new Assessment("If breathing does not remain level, may require ICU support", "Sick", "Take 2 tablets of medicol every day", "Keep under observation at all times",
					"All monitor indicates severe issue", user_13, user_4,new Date());
				assessment_repository.save(assessment_13);

				Assessment assessment_14 = new Assessment("May require hospitilisation if condition worsens", "Average", "Reduce dosage of medicol to one every two days. Do not carry out strenuous exercise at this time.", "Keep under observation at all times",
					"Normal vital signs", user_13, user_4,new Date());
				assessment_repository.save(assessment_14);

				Assessment assessment_15 = new Assessment("Bed rest recommended ", "Good", "Take 1 tablets of medicol every day", "Checkup occasionally, ideally twice a week",
					"Normal vital signs", user_13, user_4,new Date());
				assessment_repository.save(assessment_15);

				Assessment assessment_16 = new Assessment("May require hospitilisation if condition worsens", "Average", "Take 1 tablets of medicol every day", "Check occasionally",
					"Heart rate monitor indicates severe issue", user_15, user_2,new Date());
				assessment_repository.save(assessment_16);

				Assessment assessment_17 = new Assessment("If breathing does not remain level, may require ICU support", "Sick", "Take 2 tablets of medicol every day", "Keep under observation at all times",
					"Heart rate monitor indicates severe issue", user_15, user_2,new Date());
				assessment_repository.save(assessment_17);

				Assessment assessment_18 = new Assessment("if signs do not normalise immediate hospitalisation", "Sick", "Take 2 tablets of medicol every day", "Keep under observation at all times",
					"All monitor indicates severe issue", user_16, user_3,new Date());
				assessment_repository.save(assessment_18);

				Assessment assessment_19 = new Assessment("If breathing does not remain level, may require ICU support", "Average", "Reduce dosage of medicol to one every two days. Do not carry out strenuous exercise at this time.", "Checkup occasionally, ideally twice a week",
					"SPo2 monitor indicates severe issue", user_16, user_3,new Date());
				assessment_repository.save(assessment_19);

				Assessment assessment_20 = new Assessment("Hospital visit if condition worsens ", "Average", "Take 1 tablets of medicol every day", "Checkup occasionally, ideally twice a week",
					"vital sign normalizing", user_16, user_3,new Date());
				assessment_repository.save(assessment_20);

				Assessment assessment_21 = new Assessment("May require hospitilisation if condition worsens", "Good", "Take care and precautions according to condition", "Check occasionally",
					"Vital signs moderate values", user_16, user_1,new Date());
				assessment_repository.save(assessment_21);

				Assessment assessment_22 = new Assessment("Bed rest recommended ", "Good", "Healthy diet and bed rest", "Check occasionally",
					"Normal vital signs", user_16, user_1,new Date());
				assessment_repository.save(assessment_22);

        // ================ SEEDS END ================

        return args -> {
        log.info("Preloading " + user_1);
    };
  }
}
