package com.example.rzdwebapp.controller.crud;


import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.data.entity.Train;
import com.example.rzdwebapp.data.entity.TrainType;
import com.example.rzdwebapp.repository.crud.TrainRepo;
import com.example.rzdwebapp.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/train")
public class TrainController implements CrudController<Train,Integer>{
    @Autowired
    private TrainService service;
    @Autowired
    private TrainRepo repo;

    @Override
    public List<String> getHeaders() {
        return List.of("id","type","seatsNum","station","firstDate","trainBrigade","repairBrigade");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("repairBrigade", repo.getRepairBrigadeList());
        myMap.put("trainBrigade", repo.getTrainBrigadeList());
        myMap.put("station", repo.getStationList());
        myMap.put("type",List.of(TrainType.express,TrainType.passenger));
        return myMap;
    }

    @Override
    public PagedResponse<Train> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public Train getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(Train entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, Train entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}
