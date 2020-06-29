package com.example.SWT2.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    // Homepage
    @GetMapping("/")
    public String gotoHomepage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        model.addAttribute("name", name);
        return "home";
    }
    // Login
    //@PostMapping("/login")
    //public String login(){
    //    return "home";
    //}
}