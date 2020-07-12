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
import com.example.SWT2.api.Object.*;

@Controller
public class ReiseController {
    @PostMapping("/anzReise")
    public String reiseAnzeigen(@ModelAttribute("Suchanfrage0") Suchanfrage suchanfrage,HttpSession session, Model model){
        DatabaseManager db = new DatabaseManager();
        if(session.getAttribute("user") != null){
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("reise", db.suchReisen(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Suchanfrage0", new Suchanfrage());
            model.addAttribute("reisenr", db.suchReisennr(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Suchanfrage1", new Suchanfrage());
            model.addAttribute("Bewertung",0);
            model.addAttribute("AID", db.getAktivitäten(suchanfrage.getOption(), suchanfrage.getSuche()));
            session.setAttribute("such", suchanfrage);
            return "AnzReise";
        }
        else{
            model.addAttribute("user",0);
            model.addAttribute("reise", db.suchReisen(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Suchanfrage1", new Suchanfrage());
            model.addAttribute("AID", db.getAktivitäten(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Bewertung",0);
            session.setAttribute("such", suchanfrage);
            return "AnzReise";
        }
    }

    @PostMapping("/reiseBuchen")
    public String reiseBuchen(@ModelAttribute("Suchanfrage0") Suchanfrage suchanfrage, HttpSession session, Model model){
        DatabaseManager db = new DatabaseManager();
        if(session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            if(suchanfrage.getOption() != null){
            int a = suchanfrage.getVon().getYear()*365 + suchanfrage.getVon().getMonth()*30 + suchanfrage.getVon().getDay();
            int b = suchanfrage.getBis().getYear()*365 + suchanfrage.getBis().getMonth()*30 + suchanfrage.getBis().getDay();
            double c = b-a;
            double preis = c*db.getPreisvonReise(Integer.parseInt(suchanfrage.getOption()));
            int usernr = db.getUsernr(user.getVorname(), user.getNachname());
            int reisenr = Integer.parseInt(suchanfrage.getOption());
            db.addBuchung1(suchanfrage.getVon(), suchanfrage.getBis(),db.getReisebyId(reisenr), db.getUserbyId(usernr), preis);
            }

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
            model.addAttribute("user",0);
            model.addAttribute("reise", db.suchReisen(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Suchanfrage1", new Suchanfrage());
            model.addAttribute("AID", db.getAktivitäten(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Bewertung",0);
            session.setAttribute("such", suchanfrage);
            return "AnzReise";
        }
    }

    @PostMapping("/BewertAktivität")
    public String bewertAktivität(@ModelAttribute("Suchanfrage0") Suchanfrage suchanfrage, HttpSession session, Model model){
        DatabaseManager db = new DatabaseManager();
        if(session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            int usernr = db.getUsernr(user.getVorname(), user.getNachname());
            db.addBewertung(db.getUserbyId(usernr),db.getAktivitätbyId(Integer.parseInt(suchanfrage.getOption())), suchanfrage.getSuche());


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
            model.addAttribute("user",0);
            model.addAttribute("reise", db.suchReisen(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Suchanfrage1", new Suchanfrage());
            model.addAttribute("AID", db.getAktivitäten(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Bewertung",0);
            session.setAttribute("such", suchanfrage);
            return "AnzReise";
        }
    }

    @PostMapping("/ZeigBewertung")
    public String zeitBewertung(@ModelAttribute("Suchanfrage1") Suchanfrage s1, Model model, HttpSession session){
        DatabaseManager db = new DatabaseManager();
        if(session.getAttribute("user") != null){
            Suchanfrage suchanfrage = (Suchanfrage) session.getAttribute("such");
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("reise", db.suchReisen(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Suchanfrage0", new Suchanfrage());
            model.addAttribute("reisenr", db.suchReisennr(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("AID", db.getAktivitäten(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Suchanfrage1", new Suchanfrage());
            List<Object[]> ll = db.getBewertungbyTd(s1.getOption());
            model.addAttribute("Bewertung",ll);
            return "AnzReise";
        }
        else{
            Suchanfrage suchanfrage = (Suchanfrage) session.getAttribute("such");
            model.addAttribute("user",0);
            model.addAttribute("reise", db.suchReisen(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("AID", db.getAktivitäten(suchanfrage.getOption(), suchanfrage.getSuche()));
            model.addAttribute("Suchanfrage1", new Suchanfrage());;
            List<Object[]> ll = db.getBewertungbyTd(s1.getOption());
            model.addAttribute("Bewertung",ll);
            return "AnzReise";
        }
    }
}