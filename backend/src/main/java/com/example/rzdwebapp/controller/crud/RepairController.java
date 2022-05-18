package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.data.entity.Repair;
import com.example.rzdwebapp.data.entity.Train;
import com.example.rzdwebapp.data.entity.TrainType;
import com.example.rzdwebapp.repository.RepairRepo;
import com.example.rzdwebapp.repository.TrainRepo;
import com.example.rzdwebapp.service.RepairService;
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
@RequestMapping("/repair")
public class RepairController implements CrudController<Repair,Integer>{
    @Autowired
    private RepairService service;
    @Autowired
    private RepairRepo repo;


    @Override
    public List<String> getHeaders() {
        return List.of("id","train","brigade","startDate","endDate","description");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("brigade", repo.getRepairBrigadeList());
        myMap.put("train", repo.getTrainList());
        return myMap;
    }

    @Override
    public PagedResponse<Repair> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public Repair getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(Repair entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, Repair entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}
