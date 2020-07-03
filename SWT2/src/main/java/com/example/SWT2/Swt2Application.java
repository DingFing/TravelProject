package com.example.SWT2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.SWT2.Database.*;

@SpringBootApplication
public class Swt2Application {

	public static void main(String[] args) {
        SpringApplication.run(Swt2Application.class, args);
        DatabaseManager db = new DatabaseManager();
        db.gebuchteReisenVonUser(null);
    }
}