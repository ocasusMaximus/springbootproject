package cz.uhk.fim.springticketportal.db;

import cz.uhk.fim.springticketportal.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
