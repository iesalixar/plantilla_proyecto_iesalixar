package com.fitconnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

@SpringBootApplication
public class FitConnectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitConnectBackendApplication.class, args);
	}

	@Bean
	public Faker faker() {
		return new Faker();
	}

}
