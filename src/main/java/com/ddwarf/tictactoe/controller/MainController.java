package com.ddwarf.tictactoe.controller;

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
    @RequestMapping(value = "/happy")
    String index() {
        return "happy";
    }
    public void getObject() {

        //Tiger tiger = new Tiger();                       ост
        //Wolf[] wolfs = { new Wolf(), new Wolf(), new Wolf(), new Wolf(), new Wolf(), new Wolf() };                ост

        /*for (int i = 0; i < wolfs.length; i ++) {
            wolfs[i].attack(tiger);
        }*/
        /*int i = 0;
        while (i < wolfs.length) {
            wolfs[i].attack(tiger);
            i++;
        }*/

        //for(Wolf wolf: wolfs) {                 ост
       //     wolf.attack(tiger);                ост
       // }                                      ост

        // tiger.run();

        //System.out.println("tiger="+tiger.health);                   ост

        // System.out.println("wolf="+wolf.health);

        //System.out.println(Converter10to16.to16(11, 16));
        /* Massive massive = new Massive(4, 4);
        massive.print();
        massive.columnMassive(1, 3);
        massive.print(); */
    }
}
