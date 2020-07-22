package cz.uhk.fim.springticketportal.db.implementation;

import cz.uhk.fim.springticketportal.db.TicketRepository;
import cz.uhk.fim.springticketportal.db.TicketService;
import cz.uhk.fim.springticketportal.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket loadTicketById(int id) {
        return ticketRepository.findById(id).get();
    }

    @Override
    public Ticket loadTicketByDate(Date date) {
        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void removeTicketById(int id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public void removeTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

    @Override
    public void deleteAllTickets() { ticketRepository.deleteAll();
    }

    @Override
    public List<Ticket> loadAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public int getNumberOfTakenSeats(Ticket ticket) {

        int numberOfTakenSeats = 0;
        for (Ticket item : loadAllTickets()) {
            if(item.getHall().equals(ticket.getHall())) {
                numberOfTakenSeats += item.getNumberOfSeats();
            }
        }
        return numberOfTakenSeats;
    }
}
