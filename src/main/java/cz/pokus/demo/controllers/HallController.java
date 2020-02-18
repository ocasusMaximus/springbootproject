package cz.pokus.demo.controllers;

import cz.pokus.demo.db.HallService;

import cz.pokus.demo.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        hallService.createHall(new Hall(0,"J1",20));
        hallService.createHall(new Hall(1,"J2",20));
        hallService.createHall(new Hall(2,"J3",20));
        System.out.println(hallService.loadAllHalls().get(0).getId());
        System.out.println(hallService.loadAllHalls().get(1).getId());
    }

   @GetMapping(value = "/addHalls")
    public String getHalls(Model model) {
        model.addAttribute("halls", hallService.loadAllHalls());
//       System.out.println("Jsem tu");
//        System.out.println(getHalls(model));
        return "redirect:/addTicket";
    }

}
