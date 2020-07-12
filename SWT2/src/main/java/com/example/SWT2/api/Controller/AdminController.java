package com.example.SWT2.api.Controller;
import org.hibernate.boot.model.relational.Database;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.bytebuddy.matcher.ModifierMatcher.Mode;

import java.util.ArrayList;
import com.example.SWT2.Database.Tables.User;
import com.example.SWT2.Database.DatabaseManager;
import javax.servlet.http.HttpSession;
import com.*;
import java.util.Arrays;

import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
 
    @PostMapping("/UserÄndern")
    public String UserAnzeigen(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("reise", 0);
        return "home";
    }
    @PostMapping("/ReiseHinzu")
    public String ReisenHinzufügen(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("reise", 0);
        return "home";
    }
    @PostMapping("/AktivtätHinzu")
    public String AktitivätHizu(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("reise", 0);
        return "home"; 
    }
    
}