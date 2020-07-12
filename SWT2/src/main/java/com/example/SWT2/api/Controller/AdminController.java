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
import com.example.SWT2.api.Object.*;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    //Alle Nuitzer anzeigen 
    //Form machen mit Dropdown mit UserId und mit Input 0 oder 1
    //Reise neu erstellen
    //Aktivitäten neu erstellen
    //Aktivität Reise hinzufügen
    @PostMapping("/UserÄndern")
    public String UserAnzeigen(@ModelAttribute("Suchanfrage4") Suchanfrage suchanfrage,HttpSession session, Model model){
        DatabaseManager db = new DatabaseManager();
        db.setRolleByUserId(Integer.parseInt(suchanfrage.getOption()), Integer.parseInt(suchanfrage.getSuche()));

        User user = (User) session.getAttribute("user");
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

    @PostMapping("/ReiseHinzu")
    public String reisenHinzufügen(@ModelAttribute Reise reise,HttpSession session, Model model){
        DatabaseManager db = new DatabaseManager();
        db.addReise(reise.getBeschreibung(), reise.getOrt(), reise.getRegion(), reise.getLand(), reise.getPreis(), reise.getJahreszeit());

        User user = (User) session.getAttribute("user");
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

    @PostMapping("/AktivtätErstellen") //Noch machen
    public String aktitivätHinzufügen(@ModelAttribute Aktivität aktiv, HttpSession session, Model model){
        DatabaseManager db = new DatabaseManager();
        db.addAktivität(aktiv.getBeschreibung());

        User user = (User) session.getAttribute("user");
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
    
    @PostMapping("/AktivtätZuReiseHinzu") //Noch machen
    public String aktivitätZuReiseHinzufügen(@ModelAttribute("Suchanfrage5") Suchanfrage suchanfrage, HttpSession session, Model model){
        DatabaseManager db = new DatabaseManager();
        db.addBietetan(db.getAktivitätbyId(Integer.parseInt(suchanfrage.getSuche())), db.getReisebyId(Integer.parseInt(suchanfrage.getOption())));

        User user = (User) session.getAttribute("user");
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
}