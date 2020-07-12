package com.example.SWT2.api.Controller;

import org.hibernate.boot.model.relational.Database;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.bytebuddy.matcher.ModifierMatcher.Mode;

import java.util.ArrayList;
import com.example.SWT2.Database.Tables.*;
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
                User user = (User) session.getAttribute("user");
                    if(db.userAdmin(user) == true){         //Der User ist Admin
                        model.addAttribute("user", user);
                        model.addAttribute("users", db.showAlleUser());
                        model.addAttribute("userid", db.showAllUserID());
                        model.addAttribute("Suchanfrage4", new Suchanfrage());
                        model.addAttribute("reisse", new Reise());
                        model.addAttribute("aktiv", new Aktivität());
                        model.addAttribute("Suchanfrage5", new Suchanfrage());
                        model.addAttribute("Anr", db.getAllAktivtyId());
                        model.addAttribute("Reisenr", db.getAllReiseId());
                        return "home";
                    }
                    else{                          //Normaler User Login erfolgreich
                        model.addAttribute("user",user);
                        model.addAttribute("reise", db.gebuchteReisenVonUser(user.getVorname(), user.getNachname()));
                        model.addAttribute("Suchanfrage0", new Suchanfrage());
                        Suchanfrage eins = new Suchanfrage();
                        eins.setStrar(db.getNochNichtBewerteteAktivitätenVonUser(user.getVorname(), user.getNachname()));
                        model.addAttribute("Suchanfrage1", eins);
                        model.addAttribute("GebuReis", db.gebuchteReiseId(user.getVorname(), user.getNachname()));
                        model.addAttribute("Suchanfrage2", new Suchanfrage());
                        model.addAttribute("Suchanfrage3", new Suchanfrage());
                        model.addAttribute("Profilnamen", db.getProfilNamen(user.getVorname(), user.getNachname()));
                        return "home";
                    }
                }
                else{
                    model.addAttribute("user",1);
                    model.addAttribute("Suchanfrage0", new Suchanfrage());
                    return "home";
                }
    }
    // Open Login-Form
    @PostMapping("/login-form")
    public String createLoginForm(Model model) {
        model.addAttribute("User", new User());
        return "login";
    }
    // Log-in User
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpSession session){
        DatabaseManager db = new DatabaseManager();
        if(db.userVorhanden(user.getVorname(), user.getNachname(), user.getPassword())){
            if(db.userAdmin(user) == true){
                user.setRolle(1);
                session.setAttribute("user", user);
                model.addAttribute("user", user);
                model.addAttribute("users", db.showAlleUser());
                model.addAttribute("userid", db.showAllUserID());
                model.addAttribute("Suchanfrage4", new Suchanfrage());
                model.addAttribute("reisse", new Reise());
                model.addAttribute("aktiv", new Aktivität());
                model.addAttribute("Suchanfrage5", new Suchanfrage());
                model.addAttribute("Anr", db.getAllAktivtyId());
                model.addAttribute("Reisenr", db.getAllReiseId());
                return "home";
            }
            else{
                user.setRolle(0);
                session.setAttribute("user", user);
                model.addAttribute("user",user);
                model.addAttribute("reise", db.gebuchteReisenVonUser(user.getVorname(), user.getNachname()));
                model.addAttribute("Suchanfrage0", new Suchanfrage());
                Suchanfrage eins = new Suchanfrage();
                eins.setStrar(db.getNochNichtBewerteteAktivitätenVonUser(user.getVorname(), user.getNachname()));
                model.addAttribute("Suchanfrage1", eins);
                model.addAttribute("GebuReis", db.gebuchteReiseId(user.getVorname(), user.getNachname()));
                model.addAttribute("Suchanfrage2", new Suchanfrage());
                model.addAttribute("Suchanfrage3", new Suchanfrage());
                model.addAttribute("Profilnamen", db.getProfilNamen(user.getVorname(), user.getNachname()));
                return "home";
            }
        } 
        else{
            model.addAttribute("user", 0);
            model.addAttribute("Suchanfrage0", new Suchanfrage());
            return "home";
        }
    }

    @PostMapping("/register-form")
    public String createRegisterForm(Model model) {
        model.addAttribute("User", new User());
        return "register";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute User user, Model model){
        DatabaseManager db = new DatabaseManager();
        db.adduser(user.getNachname(), user.getVorname(), user.getGeburtsDat(), user.getPassword(), 0);
        model.addAttribute("user",user);
        model.addAttribute("reise", db.gebuchteReisenVonUser(user.getVorname(), user.getNachname()));
        model.addAttribute("Suchanfrage0", new Suchanfrage());
        Suchanfrage eins = new Suchanfrage();
        eins.setStrar(db.getNochNichtBewerteteAktivitätenVonUser(user.getVorname(), user.getNachname()));
        model.addAttribute("Suchanfrage1", eins);
        model.addAttribute("GebuReis", db.gebuchteReiseId(user.getVorname(), user.getNachname()));
        model.addAttribute("Suchanfrage2", new Suchanfrage());
        model.addAttribute("Suchanfrage3", new Suchanfrage());
        model.addAttribute("Profilnamen", db.getProfilNamen(user.getVorname(), user.getNachname()));
        return "home";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, Model model){
        session.removeAttribute("user");
        model.addAttribute("user",1);
        model.addAttribute("Suchanfrage0", new Suchanfrage());
        return "home";
    }
    @PostMapping("/ErstellProfil")
    public String ProfilErstellung(@ModelAttribute("Suchanfrage2") Suchanfrage suchanfrage, HttpSession session, Model model){
        if(session.getAttribute("user") != null){
            DatabaseManager db = new DatabaseManager();
            User user = (User) session.getAttribute("user");
            int usernr = db.getUsernr(user.getVorname(), user.getNachname());
            db.AddUrlaubsprofil(db.getUserbyId(usernr),db.getReisebyId(Integer.parseInt(suchanfrage.getOption())), suchanfrage.getSuche());
            model.addAttribute("user",user);
            model.addAttribute("reise", db.gebuchteReisenVonUser(user.getVorname(), user.getNachname()));
            model.addAttribute("Suchanfrage0", new Suchanfrage());
            Suchanfrage eins = new Suchanfrage();
            eins.setStrar(db.getNochNichtBewerteteAktivitätenVonUser(user.getVorname(), user.getNachname()));
            model.addAttribute("Suchanfrage1", eins);
            model.addAttribute("GebuReis", db.gebuchteReiseId(user.getVorname(), user.getNachname()));
            model.addAttribute("Suchanfrage2", new Suchanfrage());
            model.addAttribute("Suchanfrage3", new Suchanfrage());
            model.addAttribute("Profilnamen", db.getProfilNamen(user.getVorname(), user.getNachname()));
            return "home";
        }
        else{
            model.addAttribute("user", 0);
            model.addAttribute("Suchanfrage0", new Suchanfrage());
            return "home";
        }
    }
    @PostMapping("ReiseBuchenMitProfil")
    public String ReiseBuchenProfil(@ModelAttribute("Suchanfrage3") Suchanfrage suchanfrage, HttpSession session , Model model){
        if(session.getAttribute("user") != null){
            DatabaseManager db = new DatabaseManager();
            User user = (User) session.getAttribute("user");
            int a = suchanfrage.getVon().getYear()*365 + suchanfrage.getVon().getMonth()*30 + suchanfrage.getVon().getDay();
            int b = suchanfrage.getBis().getYear()*365 + suchanfrage.getBis().getMonth()*30 + suchanfrage.getBis().getDay();
            double c = b-a;
            int usernr = db.getUsernr(user.getVorname(), user.getNachname());
            int reisenr = db.getReisebyProfilId(suchanfrage.getOption());
            double preis = c*db.getPreisvonReise(reisenr);
            db.AddBuchung1(suchanfrage.getVon(), suchanfrage.getBis(), db.getReisebyId(reisenr),db.getUserbyId(usernr), preis);
            
            
            
            model.addAttribute("user",user);
            model.addAttribute("reise", db.gebuchteReisenVonUser(user.getVorname(), user.getNachname()));
            model.addAttribute("Suchanfrage0", new Suchanfrage());
            Suchanfrage eins = new Suchanfrage();
            eins.setStrar(db.getNochNichtBewerteteAktivitätenVonUser(user.getVorname(), user.getNachname()));
            model.addAttribute("Suchanfrage1", eins);
            model.addAttribute("GebuReis", db.gebuchteReiseId(user.getVorname(), user.getNachname()));
            model.addAttribute("Suchanfrage2", new Suchanfrage());
            model.addAttribute("Suchanfrage3", new Suchanfrage());
            model.addAttribute("Profilnamen", db.getProfilNamen(user.getVorname(), user.getNachname()));
            return "home";
        }
        else{
            model.addAttribute("user", 0);
            model.addAttribute("Suchanfrage0", new Suchanfrage());
            return "home";
        }
    }
}