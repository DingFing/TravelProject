package com.example.SWT2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.SWT2.Database.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Swt2Application {

	public static void main(String[] args) {
        SpringApplication.run(Swt2Application.class, args);
        /*DatabaseManager bm = new DatabaseManager();
        List<Object[]> list = bm.gebuchteReisenVonUser("Jan", "Lano");
        for(ArrayList l : list){
            for(int i=0;i<9;i++)
               System.out.println(l.get(i));
        }*/
    }
}