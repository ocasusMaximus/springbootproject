package cz.pokus.demo.controllers;

import cz.pokus.demo.db.TicketService;
import cz.pokus.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class TicketController {


    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


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
