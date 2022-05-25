package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.data.entity.RouteStation;
import com.example.rzdwebapp.repository.crud.RouteStationRepo;
import com.example.rzdwebapp.service.RouteStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/routestation")
public class RouteStationController implements CrudController<RouteStation,Integer>{
    @Autowired
    private RouteStationService service;
    @Autowired
    private RouteStationRepo repo;


    @Override
    public List<String> getHeaders() {
        return List.of("id","route","station","routeTime","timeStop");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("route", repo.getRouteList());
        myMap.put("station", repo.getStationList());
        return myMap;
    }

    @Override
    public PagedResponse<RouteStation> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public RouteStation getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(RouteStation entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, RouteStation entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}
