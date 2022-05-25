package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.data.entity.Staff;
import com.example.rzdwebapp.repository.crud.SpecializationRepo;
import com.example.rzdwebapp.repository.crud.StaffRepo;
import com.example.rzdwebapp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/staff")
public class StaffController implements  CrudController<Staff,Integer>{
    @Autowired
    private StaffService service;
    @Autowired
    private StaffRepo repo;
    @Autowired
    private SpecializationRepo specializationRepo;

    @Override
    public List<String> getHeaders() {
        return List.of("id","fullName","specialization","salary","isMale","hireDate",
                "birthdate","sector","brigade");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("specialization", specializationRepo.findAll());
        myMap.put("sector", repo.getSectorList());
        myMap.put("brigade",null);
        myMap.put("isMale",List.of(true,false));
        return myMap;
    }


    @Override
    public PagedResponse<Staff> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public Staff getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(Staff entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, Staff entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}
