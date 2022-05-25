package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.Delay;
import com.example.rzdwebapp.data.entity.DelayId;
import com.example.rzdwebapp.repository.crud.DelayRepo;
import com.example.rzdwebapp.repository.crud.RouteStationRepo;
import com.example.rzdwebapp.repository.crud.TrainScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DelayService implements CrudService<Delay, DelayId>{
    @Autowired
    private DelayRepo repo;

    @Autowired
    private TrainScheduleRepo trainScheduleRepo;
    @Autowired
    private RouteStationRepo routeStationRepo;

    @Override
    public Page<Delay> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("trainSchedule")));
    }

    @Override
    public Delay getById(DelayId id) {
        return repo.findById(id).get();
    }

    @Override
    public Delay create(Delay entity) {
        entity.setId(new DelayId(entity.getTrainSchedule().getId(),entity.getRouteStation().getId()));
        return repo.save(entity);
    }

    @Override
    public Delay update(DelayId id, Delay entity) {
        Delay toUpdate = repo.getById(id);
        toUpdate.setTime(entity.getTime());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(DelayId id) {
        repo.deleteById(id);
    }
}
