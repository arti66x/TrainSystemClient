package com.example.rzdwebapp.service;
import com.example.rzdwebapp.data.entity.Train;
import com.example.rzdwebapp.repository.crud.TrainRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TrainService implements CrudService<Train,Integer>{
    @Autowired
    private TrainRepo repo;


    @Override
    public Page<Train> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public Train getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public Train create(Train entity) {
        return repo.save(entity);
    }

    @Override
    public Train update(Integer id, Train entity) {
        Train toUpdate = repo.getById(id);
        toUpdate.setType(entity.getType());
        toUpdate.setFirstDate(entity.getFirstDate());
        toUpdate.setStation(entity.getStation());
        toUpdate.setSeatsNum(entity.getSeatsNum());
        toUpdate.setTrainBrigade(entity.getTrainBrigade());
        toUpdate.setRepairBrigade(entity.getRepairBrigade());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
