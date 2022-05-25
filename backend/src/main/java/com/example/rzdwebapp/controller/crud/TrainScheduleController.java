package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.data.entity.TrainSchedule;
import com.example.rzdwebapp.repository.crud.TrainScheduleRepo;
import com.example.rzdwebapp.service.TrainScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/trainschedule")
public class TrainScheduleController implements CrudController<TrainSchedule,Integer>{
    @Autowired
    private TrainScheduleService service;
    @Autowired
    private TrainScheduleRepo repo;

    @Override
    public List<String> getHeaders() {
        return List.of("id","direction","time","route","train");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("direction",List.of(true,false));
        myMap.put("route", repo.getRouteList());
        myMap.put("train", repo.getTrainList());
        return myMap;
    }

    @Override
    public PagedResponse<TrainSchedule> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public TrainSchedule getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(TrainSchedule entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, TrainSchedule entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}