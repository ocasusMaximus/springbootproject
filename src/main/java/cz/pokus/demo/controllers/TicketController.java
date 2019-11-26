package cz.pokus.demo.controllers;

import cz.pokus.demo.db.TicketService;
import cz.pokus.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;

@Controller
public class TicketController {

    private List<Ticket> tickets = new ArrayList<Ticket>();
//    private AtomicLong nextId = new AtomicLong();
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/hello")
    public String getHelloMessage(){
        return "HelloWorld";
    }

//    @PostMapping("/tickets")
//    public Ticket createNewTicket(@RequestBody Ticket ticket){
//        ticket.setId(nextId.incrementAndGet());
//
//        tickets.add(ticket);
//
//        return ticket;
//    }

    @GetMapping("/tickets")
    public List<Ticket> getAllTickets(){

        return tickets;
    }

    @GetMapping("/ticket")
    public String getAddTicket(Model model){
        model.addAttribute("tickets", new Ticket());
        return "addTicket";
    }

    @GetMapping("/tickets/{id}")
    public Ticket getOneTicket(@PathVariable("id") long ticketId){

        for(Ticket ticket : tickets){
            if(ticket.getId() == ticketId ){
                return ticket;
            }
        }
        throw new IllegalArgumentException();
    }
    @PostMapping("/tickets/{id}")
    public Ticket editOneTicket(@PathVariable("id") int ticketId, @RequestBody Ticket newTicket){
        for(Ticket ticket : tickets){
            if(ticket.getId() == ticketId ){
                tickets.remove(ticket);
                newTicket.setId(ticketId);
                return ticket;
            }
        }
        throw new IllegalArgumentException();
    }
    @PostMapping(value = "/source")
    public String addNewSource(@ModelAttribute Ticket ticket, Model model) {
        ticketService.createTicket(ticket);
        model.addAttribute("tickets", ticketService.loadAllTickets());
        return "tickets";
    }
    @RequestMapping({"/",""})
    public String showTicketPortal(){
//        ticketService.loadAllTickets();
        return "listOfTicket";
    }
}
