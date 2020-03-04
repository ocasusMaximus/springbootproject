package cz.uhk.fim.springticketportal.db;

import cz.uhk.fim.springticketportal.model.Hall;
import cz.uhk.fim.springticketportal.model.Ticket;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface HallService {

    Hall createHall(Hall hall);

    Hall loadHallById(int id);

    void removeHallById(int id);

    void removeHall(Hall hall);

    void deleteAllHalls();

    List<Hall> loadAllHalls();

    int getIdOfHall(@ModelAttribute Ticket ticket);
}
