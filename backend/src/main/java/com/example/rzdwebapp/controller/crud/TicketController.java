package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.data.dto.fkListDto.RouteStationFK;
import com.example.rzdwebapp.data.entity.Ticket;
import com.example.rzdwebapp.data.entity.TicketStatus;
import com.example.rzdwebapp.repository.crud.TicketRepo;
import com.example.rzdwebapp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ticket")
public class TicketController implements CrudController<Ticket,Integer>{
    @Autowired
    private TicketService service;
    @Autowired
    private TicketRepo repo;


    @Override
    public List<String> getHeaders() {
        return List.of("id","fullName","trainSchedule","startStation","endStation","time","status","luggage");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("trainSchedule", null);
        List<RouteStationFK> stationList = repo.getStationList();
        myMap.put("startStation", stationList);
        myMap.put("endStation", stationList);
        myMap.put("status",List.of(TicketStatus.booked,TicketStatus.bought,TicketStatus.returned));
        myMap.put("luggage",List.of(true,false));
        return myMap;
    }

    @Override
    public PagedResponse<Ticket> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public Ticket getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(Ticket entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, Ticket entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}
