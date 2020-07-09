package com.example.SWT2.api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import com.example.SWT2.Database.Tables.User;
import com.example.SWT2.Database.DatabaseManager;
import javax.servlet.http.HttpSession;
import com.*;
import java.util.Arrays;

@Controller
public class HomeController {
    
    // Homepage
    @GetMapping("/")
    public String gotoHomepage(HttpSession session, Model model){
            if(session.getAttribute("user") != null){
                DatabaseManager db = new DatabaseManager();
                User us = (User) session.getAttribute("user");
                    if(db.userAdmin(us) == true){         //Der User ist Admin
                        model.addAttribute("user", us);
                        model.addAttribute("reise", 0);
                        return "home";
                    }
                    else{                          //Normaler User Login erfolgreich
                        ArrayList<Object> ar = new ArrayList<Object>();
                        model.addAttribute("user",us);
                        model.addAttribute("reise", db.gebuchteReisenVonUser(us.getVorname(), us.getNachname()));
                        model.addAttribute("Suchanfrage", new Suchanfrage());
                        return "home";
                    }
                }
                else{
                    model.addAttribute("user",1);
                    model.addAttribute("Suchanfrage", new Suchanfrage());
                    return "home";
                }
    }
    // Open Login-Form
    @GetMapping("/login-form")
    public String createLoginForm(Model model) {
        model.addAttribute("User", new User());
        return "login";
    }
    // Log-in User
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpSession session){
        DatabaseManager db = new DatabaseManager();
        if(db.userVorhanden(user.getVorname(), user.getNachname(), user.getPassword())){
            if(db.userAdmin(user) == true){         //Der User ist Admin
                user.setRolle(1);
                session.setAttribute("user", user);
                model.addAttribute("user", user);
                model.addAttribute("reise",0);
                return "home";
            }
            else{                          //Normaler User Login erfolgreich
                user.setRolle(0);
                session.setAttribute("user", user);
                model.addAttribute("user", user);
                model.addAttribute("reise",db.gebuchteReisenVonUser(user.getVorname(), user.getNachname()));
                model.addAttribute("Suchanfrage", new Suchanfrage());
                return "home";
            }
        } 
        else{                            //Anmeldung nicht erfolgreich
            model.addAttribute("user", 0);
            model.addAttribute("Suchanfrage", new Suchanfrage());
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
    public String saveUser(@ModelAttribute User user){
        DatabaseManager db = new DatabaseManager();
        db.adduser(user.getNachname(), user.getVorname(), user.getGeburtsDat(), user.getPassword(), 0);
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model){
        session.removeAttribute("user");
        model.addAttribute("user", 1);
        model.addAttribute("Suchanfrage", new Suchanfrage());
        return "home";
    }
}