package cz.pokus.demo.db;

import cz.pokus.demo.model.Hall;

import java.util.Date;
import java.util.List;

public interface HallService {

    Hall createHall(Hall hall);

    Hall loadHallById(int id);

    void removeHallById(int id);

    void removeHall(Hall hall);

    void deleteAllHalls();

    List<Hall> loadAllHalls();
}
