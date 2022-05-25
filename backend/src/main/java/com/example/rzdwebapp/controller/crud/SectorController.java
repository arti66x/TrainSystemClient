package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.entity.Sector;
import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.repository.crud.SectorRepo;
import com.example.rzdwebapp.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/sector")
public class SectorController implements  CrudController<Sector,Integer>{
    @Autowired
    private SectorService service;
    @Autowired
    private SectorRepo repo;

    @Override
    public List<String> getHeaders() {
        return List.of("id","name","administrator");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("administrator", repo.getStaffList());
        return myMap;
    }


    @Override
    public PagedResponse<Sector> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public Sector getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(Sector entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, Sector entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}
