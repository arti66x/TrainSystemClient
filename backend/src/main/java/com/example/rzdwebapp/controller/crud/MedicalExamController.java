package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.data.entity.Brigade;
import com.example.rzdwebapp.data.entity.MedicalExam;
import com.example.rzdwebapp.repository.crud.BrigadeRepo;
import com.example.rzdwebapp.repository.crud.MedicalExamRepo;
import com.example.rzdwebapp.service.BrigadeService;
import com.example.rzdwebapp.service.MedicalExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/medicalexam")
public class MedicalExamController implements CrudController<MedicalExam,Integer>{
    @Autowired
    private MedicalExamService service;
    @Autowired
    private MedicalExamRepo repo;


    @Override
    public List<String> getHeaders() {
        return List.of("id","date","staff");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("staff", repo.getStaffList());
        return myMap;
    }

    @Override
    public PagedResponse<MedicalExam> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public MedicalExam getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public void create(MedicalExam entity) {
        service.create(entity);
    }

    @Override
    public void update(Integer id, MedicalExam entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Integer id) {
        service.deleteById(id);
    }
}