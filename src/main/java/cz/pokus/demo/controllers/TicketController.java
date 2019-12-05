package cz.pokus.demo.controllers;

import cz.pokus.demo.db.TicketService;
import cz.pokus.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("tickets", ticketService.loadAllTickets());
        return "tickets";
    }
    @PostMapping(value = "/ticket")
    public String addNewTicket(@ModelAttribute Ticket ticket, Model model) {
        ticketService.createTicket(ticket);
        model.addAttribute("tickets", ticketService.loadAllTickets());
        return "tickets";
    }
    @GetMapping(value = "/tickets")
    public String getTickets(Model model){
        model.addAttribute("ticket",new Ticket());
        return "addTicket";

    }

        }
