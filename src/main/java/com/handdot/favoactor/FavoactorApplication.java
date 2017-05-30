package com.handdot.favoactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class FavoactorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavoactorApplication.class, args);
	}
}
