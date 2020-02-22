package cz.pokus.demo.controllers;

import cz.pokus.demo.db.HallService;
import cz.pokus.demo.db.TicketService;
import cz.pokus.demo.model.Hall;
import cz.pokus.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class TicketController {


    private final TicketService ticketService;
    private HallService hallService;

    @Autowired
    public TicketController(TicketService ticketService, HallService hallService) {
        this.ticketService = ticketService;
        this.hallService = hallService;
    }



    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("tickets", ticketService.loadAllTickets());
        model.addAttribute("halls", hallService.loadAllHalls());
        return "tickets";
    }
    @PostMapping(value = "/createTicket")
    public String createTicket(@ModelAttribute Ticket ticket, Model model) {

        int idOfHall = hallService.getIdOfHall(ticket);
        int capacity =hallService.loadHallById(idOfHall).getCapacity() -ticketService.getNumberOfTakenSeats(ticket);
        if (capacity>= ticket.getNumberOfSeats()){
            ticketService.createTicket(ticket);
           hallService.loadHallById(idOfHall).setCapacity(capacity- ticket.getNumberOfSeats());
            System.out.println("Při creatu:" + hallService.loadHallById(idOfHall).getCapacity() + " nazev " + hallService.loadHallById(idOfHall).getName());

    } else {
           return "error";
        }
        model.addAttribute("tickets", ticketService.loadAllTickets());

        return "redirect:/";
    }



    @PostMapping(value = "/deleteTicket")
    public String deleteTicket(@ModelAttribute Ticket ticket, Model model) {
        ticketService.removeTicket(ticket);
        model.addAttribute("tickets", ticketService.loadAllTickets());
        return "redirect:/";
    }
    @PostMapping(value = "/deleteAllTickets")
    public String deleteAllTickets(@ModelAttribute Ticket ticket, Model model) {
        ticketService.deleteAllTickets();
        model.addAttribute("tickets", ticketService.loadAllTickets());
        return "redirect:/";
    }
    @GetMapping(value = "/addTicket")
    public String getTickets(Model model){
        model.addAttribute("ticket",new Ticket());
        model.addAttribute("halls", hallService.loadAllHalls());
        System.out.println("Jsem v addu: "+hallService.loadHallById(1).getCapacity());
        return "addTicket";

    }
    @GetMapping(value = "/updateTicket")
    public String getUpdateTickets(Model model, @ModelAttribute Ticket ticket){
        model.addAttribute("tickets",ticketService.loadAllTickets());
        return "addTicket";

    }

   @PostMapping (value = "/ticketUpdate/{id}")
    public String updateTicket( @ModelAttribute Ticket ticket, @PathVariable int id, Model model) {
        ticket.setId(id);
       int idOfHall = hallService.getIdOfHall(ticket);
       System.out.println("prvni" +hallService.loadHallById(idOfHall).getCapacity());
       //problem je v tom ze capacita je tady zadana a uz je aktualne snizena takze snizuji od snizene
       int capacity =hallService.loadHallById(idOfHall).getCapacity() -ticketService.getNumberOfTakenSeats(ticket);
       System.out.println(capacity);
       if (capacity>= ticket.getNumberOfSeats()){
           System.out.println("druha " +hallService.loadHallById(idOfHall).getCapacity());
           ticketService.updateTicket(ticket);
           capacity =hallService.loadHallById(idOfHall).getCapacity() -ticketService.getNumberOfTakenSeats(ticket);
           hallService.loadHallById(idOfHall).setCapacity(capacity);
           System.out.println("capacity"+capacity);
           System.out.println("treti: " +hallService.loadHallById(idOfHall).getCapacity());
        } else  {
            return "error";
        }
       model.addAttribute("tickets",  ticketService.loadAllTickets());
        return "redirect:/";
    }

}
