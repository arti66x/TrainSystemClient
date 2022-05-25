package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.Repair;
import com.example.rzdwebapp.repository.crud.RepairRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RepairService implements CrudService<Repair,Integer>{

    @Autowired
    private RepairRepo repo;


    @Override
    public Page<Repair> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public Repair getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public Repair create(Repair entity) {
        return repo.save(entity);
    }

    @Override
    public Repair update(Integer id, Repair entity) {
        Repair toUpdate = repo.getById(id);
        toUpdate.setTrain(entity.getTrain());
        toUpdate.setBrigade(entity.getBrigade());
        toUpdate.setStartDate(entity.getStartDate());
        toUpdate.setEndDate(entity.getEndDate());
        toUpdate.setDescription(entity.getDescription());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
