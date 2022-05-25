package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.RouteStation;
import com.example.rzdwebapp.data.entity.Ticket;
import com.example.rzdwebapp.exception.ValidationException;
import com.example.rzdwebapp.repository.crud.RouteStationRepo;
import com.example.rzdwebapp.repository.crud.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TicketService implements CrudService<Ticket,Integer>{
    @Autowired
    private TicketRepo repo;
    @Autowired
    private RouteStationRepo routeStationRepo;

    @Override
    public Page<Ticket> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public Ticket getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public Ticket create(Ticket entity) {
        checkEntity(entity);
        return repo.save(entity);
    }

    @Override
    public Ticket update(Integer id, Ticket entity) {
        checkEntity(entity);
        Ticket toUpdate = repo.getById(id);
        toUpdate.setFullName(entity.getFullName());
        toUpdate.setLuggage(entity.getLuggage());
        toUpdate.setStatus(entity.getStatus());
        toUpdate.setTime(entity.getTime());
        toUpdate.setStartStation(entity.getStartStation());
        toUpdate.setEndStation(entity.getEndStation());
        toUpdate.setTrainSchedule(entity.getTrainSchedule());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    private void checkEntity(Ticket e){
        RouteStation start = routeStationRepo.getById(e.getStartStation().getId());
        RouteStation end = routeStationRepo.getById(e.getEndStation().getId());
//        System.out.println(start.);
        if(!Objects.equals(start.getRoute().getId(), end.getRoute().getId())){
            throw new ValidationException("different routes of selected stations");
        }
    }
}
