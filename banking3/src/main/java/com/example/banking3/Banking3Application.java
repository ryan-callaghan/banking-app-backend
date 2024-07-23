package com.example.banking3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableWebSecurity
public class Banking3Application {

	public static void main(String[] args) {
		SpringApplication.run(Banking3Application.class, args);
	}
}
