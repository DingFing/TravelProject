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

@Controller
public class HomeController {

    // Homepage
    @GetMapping("/")
    public String gotoHomepage(){
        return "home";
    }

    // Open Register-Form
    @GetMapping("/register-form")
    public String createRegisterForm(Model model) {
        model.addAttribute("User", new User());
        return "register";
    }

    // Open Login-Form
    @GetMapping("/login-form")
    public String createLoginForm(Model model) {
        model.addAttribute("User", new User());
        return "login";
    }

    // Register user in Database
    @PostMapping("/save-user")
    public String outputData(@ModelAttribute User user){
        DatabaseManager db = new DatabaseManager();
        db.adduser(user.getNachname(), user.getVorname(), user.getGeburtsDat(), user.getPassword(), user.getKontoNr(), 0);
        return "home";
    }

    // Log-in User
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model){
        DatabaseManager db = new DatabaseManager();
        if(db.userAngemeldet(user.getVorname(), user.getNachname(), user.getPassword())){
            if(user.isAdmin()){     //Der User ist Admin
                ArrayList<Object> ar = new ArrayList<Object>();
                ar.add(user);
                System.out.println("AdminAngemeldet");
                return "adminhome";
            }else{                  //Normaler User Login erfolgreich
                ArrayList<Object> ar = new ArrayList<Object>();
                ar.add(user);
                ar.add(db.gebuchteReisenVonUser(user));
                model.addAttribute("Object",ar);
                System.out.println("Angemeldet");
                return "home";
            }
        } else {                    //Anmeldung nicht erfolgreich
            System.out.println("Nicht Angemeldet");
            return "login";
        }
    }
}