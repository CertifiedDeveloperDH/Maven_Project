package com.company.ClinicaOdontologicaB;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class ClinicaOdontologicaBApplication {

	public static void main(String[] args) {
		File log4jfile = new File("src/main/resources/log4j.properties");
		PropertyConfigurator.configure(log4jfile.getAbsolutePath());
		SpringApplication.run(ClinicaOdontologicaBApplication.class, args);
	}

}
