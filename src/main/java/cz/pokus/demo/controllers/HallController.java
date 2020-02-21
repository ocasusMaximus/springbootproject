package cz.pokus.demo.controllers;

import cz.pokus.demo.db.HallService;

import cz.pokus.demo.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.PostConstruct;


@Controller
public class HallController {


    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }
    @PostConstruct
    public void init(){
        hallService.createHall(new Hall(1,"J2",20));
        hallService.createHall(new Hall(2,"J3",20));
        for(int i =0; i < hallService.loadAllHalls().size(); i++){
            System.out.println("Na pozici: " + i +" Id: " + hallService.loadAllHalls().get(i).getId() + " hala: "+ hallService.loadAllHalls().get(i).getName() + " kapacita:" + hallService.loadAllHalls().get(i).getCapacity());
        }

    }


}
