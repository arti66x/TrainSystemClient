package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.entity.Specialization;
import com.example.rzdwebapp.data.dto.PagedResponse;
import com.example.rzdwebapp.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/specialization")
public class SpecializationController implements CrudController<Specialization,Short>
{
    @Autowired
    private SpecializationService service;

    @Override
    public List<String> getHeaders() {
        return List.of("id","name");
    }

    @Override
    public Map<String,List> getForeignKeys() {
        return null;
    }

    @Override
    public PagedResponse<Specialization> findAllPaged(Integer page, Integer size) {
        return new PagedResponse<>(service.findAllPaged(page, size));
    }

    @Override
    public Specialization getById(Short id) {
        return service.getById(id);
    }

    @Override
    public void create(Specialization entity) {
         service.create(entity);
    }

    @Override
    public void update(Short id, Specialization entity) {
        service.update(id,entity);
    }

    @Override
    public void deleteById(Short id) {
        service.deleteById(id);
    }

}
