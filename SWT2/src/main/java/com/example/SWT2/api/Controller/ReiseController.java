package com.example.SWT2.api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import com.example.SWT2.Database.DatabaseManager;
import javax.servlet.http.HttpSession;
import com.*;
import com.example.SWT2.Database.Tables.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

@Controller
public class ReiseController {
    @GetMapping("/anzReise")
    public String ReiseAnzeigen(@ModelAttribute Suchanfrage suchanfrage,HttpSession session, Model model){
        DatabaseManager bm = new DatabaseManager();
        if(session.getAttribute("user") != null){
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("reise", bm.SuchReisen(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Suchanfrage", new Suchanfrage());
            model.addAttribute("reisenr", bm.SuchReisennr(suchanfrage.getOption(), suchanfrage.getSuche()));
            System.out.println("####################################################################################");
            return "AnzReise";
        }
        else{
            model.addAttribute("user",0);
            model.addAttribute("reise", bm.SuchReisen(suchanfrage.getOption(), suchanfrage.getSuche()));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            return "AnzReise";
        }
    }
    @GetMapping("/profil-create")
    public String profil(HttpSession session, Model model){//Noch machen
        model.addAttribute("user", new Urlaubsprofile());
        return "create-profil";
    }
    @GetMapping("/reiseBuchen")
    public String buchen(@ModelAttribute Suchanfrage suchanfrage, HttpSession session, Model model){
        DatabaseManager bm = new DatabaseManager();
        double a = suchanfrage.getVon().getYear()*365 + suchanfrage.getVon().getMonth()*30 + suchanfrage.getVon().getDay();
        double b = suchanfrage.getBis().getYear()*365 + suchanfrage.getBis().getMonth()*30 + suchanfrage.getBis().getDay();
        double c = b-a;
        System.out.println("Das sind die Tage: ##############################################"+c);
        double preis = c*bm.getPreisvonReise(Integer.parseInt(suchanfrage.getOption()));
        System.out.println("Das ist der Preis: ##############################################"+preis);
        User us = (User) session.getAttribute("user");
        int usernr = bm.getUsernr(us.getVorname(), us.getNachname());
        int reisenr = Integer.parseInt(suchanfrage.getOption());
        bm.AddBuchung1(suchanfrage.getVon(), suchanfrage.getBis(),bm.getReisebyId(reisenr), bm.getUserbyId(usernr), preis);
        model.addAttribute("user",us);
        model.addAttribute("reise", bm.gebuchteReisenVonUser(us.getVorname(), us.getNachname()));
        model.addAttribute("Suchanfrage", new Suchanfrage());
        return "home";
    }
}