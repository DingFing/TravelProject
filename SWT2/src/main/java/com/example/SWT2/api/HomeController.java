package com.example.SWT2.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import com.example.SWT2.Database.Tables.User;
import com.example.SWT2.Database.DatabaseManager;
import javax.servlet.http.HttpSession;


@Controller
public class HomeController {
    
    
    // Homepage
    @GetMapping("/")
    public String gotoHomepage(){
        return "home";
    }
    // Open Login-Form
    @GetMapping("/login-form")
    public String createLoginForm(Model model) {
        model.addAttribute("User", new User());
        return "login";
    }
    // Log-in User
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model){
        DatabaseManager db = new DatabaseManager();
        if(db.userVorhanden(user.getVorname(), user.getNachname(), user.getPassword())){
            if(db.userAdmin(user) == true){         //Der User ist Admin
                user.setRolle(1);
                ArrayList<Object> ar = new ArrayList<Object>();
                ar.add(user);
                model.addAttribute("ar", ar);
                System.out.println("AdminAngemeldet");
                return "home";
            }
            else{                          //Normaler User Login erfolgreich
                ArrayList<Object> ar = new ArrayList<Object>();
                user.setRolle(0);
                ar.add(user);
                ar.add(db.gebuchteReisenVonUser(user.getVorname(), user.getNachname()));
                model.addAttribute("ar",ar);
                System.out.println("Angemeldet");
                return "home";
            }
        } 
        else{                            //Anmeldung nicht erfolgreich
            System.out.println("Nicht Angemeldet");
            return "home";
        }
    }

    // Open Register-Form
    @GetMapping("/register-form")
    public String createRegisterForm(Model model) {
        model.addAttribute("User", new User());
        return "register";
    }

    // Register user in Database
    @PostMapping("/save-user")
    public String outputData(@ModelAttribute User user){
        DatabaseManager db = new DatabaseManager();
        db.adduser(user.getNachname(), user.getVorname(), user.getGeburtsDat(), user.getPassword(), user.getKontoNr(), 0);
        return "home";
    }

    @PostMapping("/logout")
    public String logout(){
        return "home";
    }
}