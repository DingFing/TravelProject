package com.example.SWT2.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.SWT2.Database.User;

@Controller
public class HomeController {
    // Homepage
    @GetMapping("/")
    public String gotoHomepage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        model.addAttribute("name", name);
        return "home";
    }
    // Register-Form
    @GetMapping("/register-form")
    public String createLoginForm(Model model) {

        model.addAttribute("User", new User());
        return "register";
    }
    // Register Test Output
    @PostMapping("/save-user")
    public String outputData(@ModelAttribute User user){
        return "result";
    }
}