package cz.pokus.demo.controllers;

import cz.pokus.demo.db.TicketService;
import cz.pokus.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;

@Controller
public class TicketController {


    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }



//    @PostMapping("/tickets")
//    public Ticket createNewTicket(@RequestBody Ticket ticket){
//        ticket.setId(nextId.incrementAndGet());
//
//        tickets.add(ticket);
//
//        return ticket;
//    }

//    @GetMapping("/tickets")
//    public List<Ticket> getAllTickets(){
//
//        return tickets;
//    }

    @GetMapping("/ticket")
    public String getAddTicket(Model model){
        model.addAttribute("tickets", new Ticket());
        return "addTicket";
    }
    @PutMapping("/error")
    public String errorMessage(){
        return "Your request did not come through!";
    }
//    @GetMapping("/tickets/{id}")
//    public Ticket getOneTicket(@PathVariable("id") long ticketId){
//
//        for(Ticket ticket : tickets){
//            if(ticket.getId() == ticketId ){
//                return ticket;
//            }
//        }
//        throw new IllegalArgumentException();
//    }

//    @PostMapping("/tickets")
//    public Ticket editOneTicket(@PathVariable("id") int ticketId, @RequestBody Ticket newTicket){
//        for(Ticket ticket : tickets){
//            if(ticket.getId() == ticketId ){
//                tickets.remove(ticket);
//                newTicket.setId(ticketId);
//                return ticket;
//            }
//        }
//        throw new IllegalArgumentException();
//    }
    @PostMapping("/ticket")
    public String addNewTicket(@ModelAttribute Ticket ticket, Model model) {
        ticketService.createTicket(ticket);
        model.addAttribute("listOfTicket", ticketService.loadAllTickets());
        return "listOfTicket";
    }
    @GetMapping("/tickets")
    public String getTickets(Model model){
        model.addAttribute("ticket",new Ticket());
        return "addTicket";

    }
    @RequestMapping({"/",""})
    public String showTicketPortal(Model model){
        model.addAttribute("tickets", ticketService.loadAllTickets());
        return "listOfTicket";
    }
}
