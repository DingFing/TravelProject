package com.example.SWT2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.DriverManager;

@SpringBootApplication
public class Swt2Application {

	public static void main(String[] args) {
		SpringApplication.run(Swt2Application.class, args);

	}
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Das ist eine Ausgabe");
		};
	}
}
