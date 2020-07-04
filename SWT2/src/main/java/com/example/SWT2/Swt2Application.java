package com.example.SWT2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.SWT2.Database.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
@SpringBootApplication
public class Swt2Application {

	public static void main(String[] args) {
        SpringApplication.run(Swt2Application.class, args);
        java.sql.Date d1 = new java.sql.Date(2020,06,21);
        java.sql.Date d2 = new java.sql.Date(2020,06,29);
        int a = d1.getTime();
        //DatabaseManager db = new DatabaseManager();
        //db.gebuchteReisenVonUser(null);
    }
}