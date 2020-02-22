package cz.pokus.demo.db;

import cz.pokus.demo.model.Hall;
import cz.pokus.demo.model.Ticket;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;
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
