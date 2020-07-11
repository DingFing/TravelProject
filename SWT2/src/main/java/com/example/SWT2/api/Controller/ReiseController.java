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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import java.util.Arrays;


@Controller
public class ReiseController {
    @GetMapping("/anzReise")
    public String ReiseAnzeigen(@ModelAttribute Suchanfrage suchanfrage,HttpSession session, Model model){
        DatabaseManager bm = new DatabaseManager();
        if(session.getAttribute("user") != null){
            model.addAttribute("user", session.getAttribute("user"));
            List<Object[]> li = bm.SuchReisen(suchanfrage.getOption(), suchanfrage.getSuche());
            model.addAttribute("reise", li);
            model.addAttribute("Suchanfrage", new Suchanfrage());
            model.addAttribute("reisenr", bm.SuchReisennr(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Suchanfrage1", new Suchanfrage());
            ArrayList h = new ArrayList();
            String[] rr;
            for(Object [] r: li){
                List<Object[]> q = (List<Object[]>) r[8];
                for(Object[] z: q){
                    System.out.println("########################: "+z[0]+"  ");
                    h.add(z[0]);
                }
                

            }
            return "AnzReise";
        }
        else{
            model.addAttribute("user",0);
            model.addAttribute("reise", bm.SuchReisen(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Suchanfrage1", new Suchanfrage());
            return "AnzReise";
        }
    }
    @PostMapping("/profil-create")//Noch machen
    public String profil(HttpSession session, Model model){
        model.addAttribute("user", new Urlaubsprofile());
        return "create-profil";
    }
    @GetMapping("/reiseBuchen")
    public String buchen(@ModelAttribute Suchanfrage suchanfrage, HttpSession session, Model model){
        DatabaseManager dm = new DatabaseManager();
        int a = suchanfrage.getVon().getYear()*365 + suchanfrage.getVon().getMonth()*30 + suchanfrage.getVon().getDay();
        int b = suchanfrage.getBis().getYear()*365 + suchanfrage.getBis().getMonth()*30 + suchanfrage.getBis().getDay();
        double c = b-a;
        double preis = c*dm.getPreisvonReise(Integer.parseInt(suchanfrage.getOption()));
        User us = (User) session.getAttribute("user");
        int usernr = dm.getUsernr(us.getVorname(), us.getNachname());
        int reisenr = Integer.parseInt(suchanfrage.getOption());
        dm.AddBuchung1(suchanfrage.getVon(), suchanfrage.getBis(),dm.getReisebyId(reisenr), dm.getUserbyId(usernr), preis);
        model.addAttribute("user",us);
        model.addAttribute("reise", dm.gebuchteReisenVonUser(us.getVorname(), us.getNachname()));
        model.addAttribute("Suchanfrage", new Suchanfrage());
        Suchanfrage eins = new Suchanfrage();
        eins.setStrar(dm.getNochNichtBewerteteAktivitätenVonUser(us.getVorname(), us.getNachname()));
        model.addAttribute("Suchanfrage1", new Suchanfrage());
        return "home";
    }
    @PostMapping("/BewertAktivität")//Bewertung in Tabelle einfügen und wieder zu home returnen
    public String BewertAktivität(@ModelAttribute("Suchanfrage1") Suchanfrage suchanfrage, HttpSession session, Model model){
        DatabaseManager dm = new DatabaseManager();
        User us = (User) session.getAttribute("user");
        int usernr = dm.getUsernr(us.getVorname(), us.getNachname());
        System.out.println("######################"+suchanfrage.getOption() + "#####################################"+suchanfrage.getSuche());
        System.out.println("#######################################Bewertung: "+suchanfrage.getSuche());
        System.out.println("#######################################Usernr: "+usernr);
        System.out.println("#######################################Anr: "+Integer.parseInt(suchanfrage.getOption()));
        /*
        User a = dm.getUserbyId(usernr);
        Aktivität aa = dm.getAktivitätbyId(Integer.parseInt(suchanfrage.getOption()));
        System.out.println("#######################################User: "+);
        System.out.println("#######################################Aktivität: "+);
        */
        dm.AddBewertung(dm.getUserbyId(usernr),dm.getAktivitätbyId(Integer.parseInt(suchanfrage.getOption())), suchanfrage.getSuche());
        //dm.AddBewertung(UserNr, ANr, Bewertung);//Geht noch nicht



        model.addAttribute("user", us);
        model.addAttribute("reise",dm.gebuchteReisenVonUser(us.getVorname(), us.getNachname()));
        model.addAttribute("Suchanfrage", new Suchanfrage());
        Suchanfrage eins = new Suchanfrage();
        eins.setStrar(dm.getNochNichtBewerteteAktivitätenVonUser(us.getVorname(), us.getNachname()));
        model.addAttribute("Suchanfrage1", eins);
        return "home";
    }

    @PostMapping("/ZeigBewertung")
    public String ZeitBewert(@ModelAttribute("Suchanfrage1") Suchanfrage suchanfrage){

        return null;
    }
}