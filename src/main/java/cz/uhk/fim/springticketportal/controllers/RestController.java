package cz.uhk.fim.springticketportal.controllers;

import cz.uhk.fim.springticketportal.db.HallService;
import cz.uhk.fim.springticketportal.db.TicketService;
import cz.uhk.fim.springticketportal.model.Hall;
import cz.uhk.fim.springticketportal.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
        int capacity = hallService.loadHallById(idOfHall).getCapacity();

        if (capacity >= ticket.getNumberOfSeats()) {
            ticketService.createTicket(ticket);
            updatedHall.setCapacity(capacity - ticket.getNumberOfSeats());
            hallService.createHall(updatedHall);


        } else {
            return "error";
        }
        model.addAttribute("tickets", ticketService.loadAllTickets());

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
        initHalls();
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("halls", hallService.loadAllHalls());
        return "addTicket";

    }

    @GetMapping(value = "/editTicket")
    public String getEditTicket(Model model, @ModelAttribute Ticket ticket) {
        initHalls();
        model.addAttribute("halls", hallService.loadAllHalls());
        return "editTicket";

    }

    @PostMapping(value = "/updateTicket/{id}")
    public String updateTicket(@ModelAttribute Ticket ticket, @PathVariable int id, Model model) {
        Ticket dbTicket = ticketService.loadTicketById(id);
        int oldTicketNoS = dbTicket.getNumberOfSeats();

        int oldIdOfHall = hallService.getIdOfHall(dbTicket);
        int idOfHall = hallService.getIdOfHall(ticket);

        Hall oldHall = hallService.loadHallById(oldIdOfHall);
        Hall updatedHall = hallService.loadHallById(idOfHall);

        int capacity;
        if (oldHall.getId() != updatedHall.getId()) {
            capacity = updatedHall.getCapacity();
        } else {
            capacity = oldHall.getCapacity() + oldTicketNoS;
        }

        if (capacity >= ticket.getNumberOfSeats()) {

            ticket.setId(id);
            ticketService.updateTicket(ticket);

            if (oldHall.getId() != updatedHall.getId()) {
                int oldCapacity = oldHall.getCapacity() + oldTicketNoS;
                oldHall.setCapacity(oldCapacity);
                hallService.createHall(oldHall);
            }

            updatedHall.setCapacity(capacity - ticket.getNumberOfSeats());
            hallService.createHall(updatedHall);

        } else {
            return "error";
        }
        model.addAttribute("tickets", ticketService.loadAllTickets());
        return "redirect:/";
    }

    private void initHalls() {
        if (hallService.loadAllHalls().size() == 0) {
            Hall j2 = new Hall();
            j2.setId(1);
            j2.setCapacity(100);
            j2.setName("J2");
            Hall j3 = new Hall();
            j3.setId(2);
            j3.setCapacity(100);
            j3.setName("J3");

            hallService.createHall(j2);
            hallService.createHall(j3);
        }
    }

}
