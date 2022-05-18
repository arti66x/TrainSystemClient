package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.entity.Station;
import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/station")
public class StationController implements CrudController<Station,Integer> {
    @Autowired
    private StationService service;

    @Override
    public List<String> getHeaders() {
        return List.of("id","city","name");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        return null;
    }

    @Override
    public PagedResponse<Station> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public Station getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(Station entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, Station entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}
