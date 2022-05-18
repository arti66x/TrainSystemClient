package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.data.entity.Brigade;
import com.example.rzdwebapp.data.entity.CanceledRun;
import com.example.rzdwebapp.repository.CanceledRunRepo;
import com.example.rzdwebapp.service.CanceledRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/canceledrun")
public class CanceledRunController implements CrudController<CanceledRun,Integer>{
    @Autowired
    private CanceledRunService service;
    @Autowired
    private CanceledRunRepo repo;


    @Override
    public List<String> getHeaders() {
        return List.of("id","trainSchedule","description");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("trainSchedule", null);
        return myMap;
    }
    @GetMapping("/fk-id")
    public List<String> getFkId() {
        return List.of("trainSchedule");
    }

    @Override
    public PagedResponse<CanceledRun> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public CanceledRun getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(CanceledRun entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, CanceledRun entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}