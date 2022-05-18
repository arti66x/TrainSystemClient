package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.CanceledRun;
import com.example.rzdwebapp.data.entity.Delay;
import com.example.rzdwebapp.data.entity.DelayId;
import com.example.rzdwebapp.repository.CanceledRunRepo;
import com.example.rzdwebapp.repository.DelayRepo;
import com.example.rzdwebapp.repository.TrainScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DelayService implements CrudService<Delay, DelayId>{
    @Autowired
    private DelayRepo repo;

    @Override
    public Page<Delay> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public Delay getById(DelayId id) {
        return repo.findById(id).get();
    }

    @Override
    public Delay create(Delay entity) {
        return repo.save(entity);
    }

    @Override
    public Delay update(DelayId id, Delay entity) {
        Delay toUpdate = repo.getById(id);
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(DelayId id) {
        repo.deleteById(id);
    }
}
