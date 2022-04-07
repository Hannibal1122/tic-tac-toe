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
    public String main(@RequestParam(name = "name", required = false, defaultValue = "Byryndyk") String name, Model model) {
        model.addAttribute("apple", name);
        getObject();
        return "main";
    }
    @RequestMapping(value = "/niam")
    public String niam(@RequestParam(name = "name", required = false, defaultValue = "Byryndyk") String name, Model model) {
        model.addAttribute("name", name);
        return "niam";
    }
    @RequestMapping(value = "/Gomer")
    public String gomer(@RequestParam(name = "name", required = false, defaultValue = "Byryndyk") String name, Model model) {
        model.addAttribute("name", name);
        return "Gomer";
    }
    public void getObject() {
        Tiger tiger = new Tiger();
        Wolf[] wolfs = { new Wolf(), new Wolf(), new Wolf(), new Wolf(), new Wolf(), new Wolf() };
        /*for (int i = 0; i < wolfs.length; i ++) {
            wolfs[i].attack(tiger);
        }*/
        /*int i = 0;
        while (i < wolfs.length) {
            wolfs[i].attack(tiger);
            i++;
        }*/
        for(Wolf wolf: wolfs) {
            wolf.attack(tiger);
        }
        // tiger.run();
        System.out.println("tiger="+tiger.health);
        // System.out.println("wolf="+wolf.health);
    }
}
