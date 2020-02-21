package cz.pokus.demo.controllers;

import cz.pokus.demo.db.HallService;
import cz.pokus.demo.db.TicketService;
import cz.pokus.demo.model.Hall;
import cz.pokus.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
        return "tickets";
    }
    @PostMapping(value = "/createTicket")
    public String createTicket(@ModelAttribute Ticket ticket, Model model) {
        //kdyz bude misto vytvor ticketu vratit response  ze neni misto
        //zobrazit errorovou stranku kvuli nedostatku mista
        if (hallService.loadHallById(1).getCapacity() > ticket.getNumberOfSeats()){
            ticketService.createTicket(ticket);
    } else {
            System.out.println("Nedostatek místa");
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
        if(hallService.loadHallById(1).getCapacity() > ticket.getNumberOfSeats()) {
            ticketService.updateTicket(ticket);
        } else  {
            System.out.println("Neni misto!");
        }
       model.addAttribute("tickets",  ticketService.loadAllTickets());
        return "redirect:/";
    }

}
