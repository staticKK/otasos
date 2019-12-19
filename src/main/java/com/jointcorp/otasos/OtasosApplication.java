package com.jointcorp.otasos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OtasosApplication /*extends SpringBootServletInitializer*/ {

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OtassoApplication.class);
	}*/

	public static void main(String[] args) {

		SpringApplication.run(OtasosApplication.class, args);
	}
}
