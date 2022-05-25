package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.data.entity.Brigade;
import com.example.rzdwebapp.repository.crud.BrigadeRepo;
import com.example.rzdwebapp.service.BrigadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/brigade")
public class BrigadeController implements CrudController<Brigade,Integer>{
    @Autowired
    private BrigadeService service;
    @Autowired
    private BrigadeRepo repo;


    @Override
    public List<String> getHeaders() {
        return List.of("id","type","sector");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("sector", repo.getSectorList());
        myMap.put("type",List.of("бригада ремонтников","локомотивная бригада"));
        return myMap;
    }

    @Override
    public PagedResponse<Brigade> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public Brigade getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(Brigade entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, Brigade entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}