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
    @PostMapping(value = "/createTicket")
    public String createTicket(@ModelAttribute Ticket ticket, Model model) {
        ticketService.createTicket(ticket);
        System.out.println(ticket.getId());
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
        ticketService.updateTicket(ticket);
       model.addAttribute("tickets",  ticketService.loadAllTickets());
        return "redirect:/";
    }

}
