package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.entity.Route;
import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/route")
public class RouteController implements CrudController<Route,Integer>{
    @Autowired
    private RouteService service;

    @Override
    public List<String> getHeaders() {
        return List.of("id","name","price");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        return null;
    }


    @Override
    public PagedResponse<Route> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public Route getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(Route entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, Route entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}
