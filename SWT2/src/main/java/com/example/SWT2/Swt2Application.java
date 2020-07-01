package com.example.SWT2;

import com.example.SWT2.Database.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Swt2Application {

	public static void main(String[] args) {
        SpringApplication.run(Swt2Application.class, args);
        DatabaseManager db = new DatabaseManager();
        java.sql.Date da = new java.sql.Date(1998,06,21);
        db.adduser("Jan", "Lano",da,"ZZ",1123456789,0);
    }
}