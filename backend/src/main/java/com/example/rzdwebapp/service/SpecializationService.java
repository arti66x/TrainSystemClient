package com.example.rzdwebapp.service;


import com.example.rzdwebapp.data.entity.Specialization;
import com.example.rzdwebapp.repository.crud.SpecializationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SpecializationService implements CrudService<Specialization,Short>{
    @Autowired
    private SpecializationRepo repo;

    @Override
    public Page<Specialization> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public Specialization getById(Short id) {
        return repo.findById(id).get();
    }

    @Override
    public Specialization create(Specialization entity) {
        return repo.save(entity);
    }

    @Override
    public Specialization update(Short id, Specialization entity) {
        Specialization toUpdate = repo.getById(id);
        toUpdate.setName(entity.getName());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Short id) {
        repo.deleteById(id);
    }
}
