package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.RouteStation;
import com.example.rzdwebapp.repository.crud.RouteStationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RouteStationService implements CrudService<RouteStation,Integer>{
    @Autowired
    private RouteStationRepo repo;

    @Override
    public Page<RouteStation> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public RouteStation getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public RouteStation create(RouteStation entity) {
        return repo.save(entity);
    }

    @Override
    public RouteStation update(Integer id, RouteStation entity) {
        RouteStation toUpdate = repo.getById(id);
        toUpdate.setRoute(entity.getRoute());
        toUpdate.setStation(entity.getStation());
        toUpdate.setRouteTime(entity.getRouteTime());
        toUpdate.setTimeStop(entity.getTimeStop());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
