package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.Brigade;
import com.example.rzdwebapp.repository.crud.BrigadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BrigadeService implements CrudService<Brigade,Integer>{
    @Autowired
    private BrigadeRepo repo;

    @Override
    public Page<Brigade> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public Brigade getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public Brigade create(Brigade entity) {
        return repo.save(entity);
    }

    @Override
    public Brigade update(Integer id, Brigade entity) {
        Brigade toUpdate = repo.getById(id);
        toUpdate.setType(entity.getType());
        toUpdate.setSector(entity.getSector());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
