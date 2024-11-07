package com.medilabosolutions.type2diabetesfinder.patientservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Entry point for the Patient Service Application.
 *
 * This class is annotated with @SpringBootApplication indicating it is a Spring Boot application.
 * The @PropertySource annotation is used to specify the location of the external properties file db.properties.
 *
 * Uses the SLF4J Logging API as indicated by the @Slf4j annotation.
 */
@Slf4j
@SpringBootApplication
@PropertySource("file:${user.dir}/**/db.properties")
public class PatientServiceApplication {

	/**
	 * The main method which serves as the entry point for the Patient Service Application.
	 *
	 * @param args command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApplication.class, args);
	}

}
