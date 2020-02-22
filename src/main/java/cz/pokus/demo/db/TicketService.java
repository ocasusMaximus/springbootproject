package cz.pokus.demo.db;

import cz.pokus.demo.model.Hall;
import cz.pokus.demo.model.Ticket;

import java.util.Date;
import java.util.List;

public interface TicketService {

    Ticket createTicket(Ticket ticket);

    Ticket loadTicketById(int id);

    Ticket loadTicketByDate(Date date);

    Ticket updateTicket(Ticket ticket);

    void removeTicketById(int id);

    void removeTicket(Ticket ticket);

    void deleteAllTickets();

    List<Ticket> loadAllTickets();

    int getNumberOfTakenSeats(Ticket ticket);
}
