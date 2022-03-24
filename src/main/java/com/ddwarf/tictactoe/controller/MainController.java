package com.ddwarf.tictactoe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @RequestMapping(value = "/main")
    public String main(@RequestParam(name = "name", required = false, defaultValue = "Wl") String name, Model model) {
        model.addAttribute("name", name);
        return "main";
    }
    @RequestMapping(value = "/niam")
    public String niam(@RequestParam(name = "name", required = false, defaultValue = "Wl") String name, Model model) {
        model.addAttribute("name", name);
        return "niam";
    }
}
