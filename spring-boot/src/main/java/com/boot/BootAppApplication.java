package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
public class BootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootAppApplication.class, args);
	}

}
