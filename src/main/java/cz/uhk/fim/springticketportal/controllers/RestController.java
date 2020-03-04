package cz.uhk.fim.springticketportal.controllers;

import cz.uhk.fim.springticketportal.db.HallService;
import cz.uhk.fim.springticketportal.db.TicketService;
import cz.uhk.fim.springticketportal.model.Hall;
import cz.uhk.fim.springticketportal.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


@Controller
public class RestController {


    private final TicketService ticketService;
    private HallService hallService;

    @Autowired
    public RestController(TicketService ticketService, HallService hallService) {
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
        int idOfHall = hallService.getIdOfHall(ticket);
        Hall updatedHall = hallService.loadHallById(idOfHall);
        // System.out.println("Jsem v creatu pred pridanim do modelu: " + hallService.loadHallById(idOfHall).getCapacity());
        int capacity = hallService.loadHallById(idOfHall).getCapacity();
        if (capacity >= ticket.getNumberOfSeats()) {

            ticketService.createTicket(ticket);
            System.out.println(ticket.getId());
            hallService.loadHallById(idOfHall).setCapacity(capacity - ticket.getNumberOfSeats());
            hallService.createHall(updatedHall);
            //System.out.println("Při creatu:" + hallService.loadHallById(idOfHall).getCapacity() + " nazev " + hallService.loadHallById(idOfHall).getName());

        } else {
            return "error";
        }
        model.addAttribute("tickets", ticketService.loadAllTickets());
        //  System.out.println("Jsem v creatu po pridani do modelu: " + hallService.loadHallById(idOfHall).getCapacity());
        return "redirect:/";
    }


    @PostMapping(value = "/deleteTicket")
    public String deleteTicket(@ModelAttribute Ticket ticket, Model model) {
        int tickNoS = ticket.getNumberOfSeats();
        int idOfHall = hallService.getIdOfHall(ticket);
        Hall updatedHall = hallService.loadHallById(idOfHall);
        int capacity = updatedHall.getCapacity() + tickNoS;
        ticketService.removeTicket(ticket);
        updatedHall.setCapacity(capacity);
        hallService.createHall(updatedHall);

        model.addAttribute("tickets", ticketService.loadAllTickets());
        return "redirect:/";
    }

    @PostMapping(value = "/deleteAllTickets")
    public String deleteAllTickets(Model model) {
        for (Ticket ticket : ticketService.loadAllTickets()) {
            System.out.println(ticket.getId());
            int tickNoS = ticket.getNumberOfSeats();
            int idOfHall = hallService.getIdOfHall(ticket);
            Hall updatedHall = hallService.loadHallById(idOfHall);
            int capacity = updatedHall.getCapacity() + tickNoS;
            updatedHall.setCapacity(capacity);
            hallService.createHall(updatedHall);
        }
        ticketService.deleteAllTickets();
        model.addAttribute("tickets", ticketService.loadAllTickets());
        model.addAttribute("halls", hallService.loadAllHalls());
        return "redirect:/";
    }

    @GetMapping(value = "/addTicket")
    public String getTickets(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("halls", hallService.loadAllHalls());
        return "addTicket";

    }

    @GetMapping(value = "/updateTicket")
    public String getUpdateTickets(Model model, @ModelAttribute Ticket ticket) {
        model.addAttribute("tickets", ticketService.loadAllTickets());
        model.addAttribute("halls", hallService.loadAllHalls());
        return "addTicket";

    }

    @PostMapping(value = "/ticketUpdate/{id}")
    public String updateTicket(@ModelAttribute Ticket ticket, @PathVariable int id, Model model) {
        int oldTicketNoS = ticketService.loadTicketById(id).getNumberOfSeats();
        int oldIdOfHall = hallService.getIdOfHall(ticketService.loadTicketById(id));
        int idOfHall = hallService.getIdOfHall(ticket);
        Hall oldHall = hallService.loadHallById(oldIdOfHall);
        Hall updatedHall = hallService.loadHallById(idOfHall);
        int capacity = oldHall.getCapacity() + oldTicketNoS;
        if (capacity >= ticket.getNumberOfSeats()) {
            oldHall.setCapacity(capacity);
            ticketService.updateTicket(ticket);
            capacity = updatedHall.getCapacity();

            updatedHall.setCapacity(capacity - ticket.getNumberOfSeats());

            hallService.createHall(updatedHall);

        } else {
            return "error";
        }
        model.addAttribute("tickets", ticketService.loadAllTickets());
        return "redirect:/";
    }

}
