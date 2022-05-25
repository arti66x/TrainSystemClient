package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.Route;
import com.example.rzdwebapp.repository.crud.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RouteService implements CrudService<Route,Integer>{
    @Autowired
    private RouteRepo repo;

    @Override
    public Page<Route> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public Route getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public Route create(Route entity) {
        return repo.save(entity);
    }

    @Override
    public Route update(Integer id, Route entity) {
        Route toUpdate = repo.getById(id);
        toUpdate.setName(entity.getName());
        toUpdate.setPrice(entity.getPrice());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
