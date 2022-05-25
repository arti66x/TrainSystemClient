package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.Station;
import com.example.rzdwebapp.repository.crud.StationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StationService implements CrudService<Station,Integer>{
    @Autowired
    private StationRepo repo;

    @Override
    public Page<Station> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public Station getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public Station create(Station entity) {
        return repo.save(entity);
    }

    @Override
    public Station update(Integer id, Station entity) {
        Station toUpdate = repo.getById(id);
        toUpdate.setCity(entity.getCity());
        toUpdate.setName(entity.getName());

        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
