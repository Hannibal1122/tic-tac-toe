package com.ddwarf.tictactoe.controller;

import com.ddwarf.tictactoe.core.Animal;
import com.ddwarf.tictactoe.core.Tiger;
import com.ddwarf.tictactoe.core.Wolf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @RequestMapping(value = "/main")
    public String main(@RequestParam(name = "name", required = false, defaultValue = "Wl") String name, Model model) {
        model.addAttribute("name", name);
        getObject();
        return "main";
    }
    @RequestMapping(value = "/niam")
    public String niam(@RequestParam(name = "name", required = false, defaultValue = "Wl") String name, Model model) {
        model.addAttribute("name", name);
        return "niam";
    }
    public void getObject() {
        Tiger tiger = new Tiger();
        Wolf wolf = new Wolf();
        tiger.attack(wolf);
        wolf.attack(tiger);
        System.out.println("tiger="+tiger.health);
        System.out.println("wolf="+wolf.health);
    }
}
