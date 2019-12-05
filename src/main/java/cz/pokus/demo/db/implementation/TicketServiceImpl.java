package cz.pokus.demo.db.implementation;

import cz.pokus.demo.db.TicketRepository;
import cz.pokus.demo.db.TicketService;
import cz.pokus.demo.model.Ticket;
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
        return ticketRepository.getOne(id);
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
    public List<Ticket> loadAllTickets() {
        return ticketRepository.findAll();
    }
}
