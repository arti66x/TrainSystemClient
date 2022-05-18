package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.Repair;
import com.example.rzdwebapp.data.entity.TechInspection;
import com.example.rzdwebapp.repository.RepairRepo;
import com.example.rzdwebapp.repository.TechInspectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TechInspectionService implements CrudService<TechInspection,Integer>{
    @Autowired
    private TechInspectionRepo repo;

    @Override
    public Page<TechInspection> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public TechInspection getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public TechInspection create(TechInspection entity) {
        return repo.save(entity);
    }

    @Override
    public TechInspection update(Integer id, TechInspection entity) {
        TechInspection toUpdate = repo.getById(id);
        toUpdate.setTrain(entity.getTrain());
        toUpdate.setBrigade(entity.getBrigade());
        toUpdate.setDate(entity.getDate());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
