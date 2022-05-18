package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.data.dto.fkListDto.RouteStationFK;
import com.example.rzdwebapp.data.entity.CanceledRun;
import com.example.rzdwebapp.data.entity.Delay;
import com.example.rzdwebapp.data.entity.DelayId;
import com.example.rzdwebapp.repository.CanceledRunRepo;
import com.example.rzdwebapp.repository.DelayRepo;
import com.example.rzdwebapp.service.CanceledRunService;
import com.example.rzdwebapp.service.DelayService;
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
@RequestMapping("/delay")
public class DelayController implements CrudController<Delay, String>{
    @Autowired
    private DelayService service;
    @Autowired
    private DelayRepo repo;


    @Override
    public List<String> getHeaders() {
        return List.of("trainSchedule","routeStation","time");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("trainSchedule", null);
        myMap.put("routeStation", repo.getStationList());
        return myMap;
    }
    @GetMapping("/fk-id")
    public List<String> getFkId() {
        return List.of("trainSchedule","routeStation");
    }

    @Override
    public PagedResponse<Delay> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public Delay getById(String id) {
        System.out.println(id);
        return service.getById(new DelayId(id));
    }

    @Override
    public void create(Delay entity) {
        service.create(entity);
    }

    @Override
    public void update(String id, Delay entity) {
        service.update(new DelayId(id),entity);
    }

    @Override
    public void deleteById(String id) {
        service.deleteById(new DelayId(id));
    }
}
