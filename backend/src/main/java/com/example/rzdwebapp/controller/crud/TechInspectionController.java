package com.example.rzdwebapp.controller.crud;


import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.data.entity.Repair;
import com.example.rzdwebapp.data.entity.TechInspection;
import com.example.rzdwebapp.repository.RepairRepo;
import com.example.rzdwebapp.repository.TechInspectionRepo;
import com.example.rzdwebapp.service.RepairService;
import com.example.rzdwebapp.service.TechInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/techinspection")
public class TechInspectionController implements CrudController<TechInspection,Integer>{
    @Autowired
    private TechInspectionService service;
    @Autowired
    private TechInspectionRepo repo;


    @Override
    public List<String> getHeaders() {
        return List.of("id","train","brigade","date");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("brigade", repo.getRepairBrigadeList());
        myMap.put("train", repo.getTrainList());
        return myMap;
    }

    @Override
    public PagedResponse<TechInspection> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public TechInspection getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(TechInspection entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, TechInspection entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}