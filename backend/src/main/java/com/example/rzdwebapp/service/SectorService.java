package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.Sector;
import com.example.rzdwebapp.repository.crud.SectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SectorService implements CrudService<Sector,Integer>{
    @Autowired
    private SectorRepo repo;

    @Override
    public Page<Sector> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public Sector getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public Sector create(Sector entity) {
        return repo.save(entity);
    }

    @Override
    public Sector update(Integer id, Sector entity) {
        Sector toUpdate = repo.getById(id);
        toUpdate.setName(entity.getName());
        toUpdate.setAdministrator(entity.getAdministrator());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
