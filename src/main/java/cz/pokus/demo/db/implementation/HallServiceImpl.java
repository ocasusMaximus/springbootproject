package cz.pokus.demo.db.implementation;

import cz.pokus.demo.db.HallRepository;
import cz.pokus.demo.db.HallService;
import cz.pokus.demo.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HallServiceImpl implements HallService {


    @Autowired
    private HallRepository hallRepository;

    @Override
    public Hall createHall(Hall hall) {
        return hallRepository.save(hall);
    }

    @Override
    public Hall loadHallById(int id) {
        return hallRepository.getOne(id);
    }

    @Override
    public void removeHallById(int id) {
        hallRepository.deleteById(id);
    }

    @Override
    public void removeHall(Hall hall) {
        hallRepository.delete(hall);
    }

    @Override
    public void deleteAllHalls() {
        hallRepository.deleteAll();
    }

    @Override
    public List<Hall> loadAllHalls() {
        return hallRepository.findAll();
    }
}
