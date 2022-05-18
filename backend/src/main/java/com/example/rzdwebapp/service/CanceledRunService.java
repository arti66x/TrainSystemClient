package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.Brigade;
import com.example.rzdwebapp.data.entity.CanceledRun;
import com.example.rzdwebapp.repository.BrigadeRepo;
import com.example.rzdwebapp.repository.CanceledRunRepo;
import com.example.rzdwebapp.repository.TrainScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Service
public class CanceledRunService implements CrudService<CanceledRun,Integer>{
    @Autowired
    private CanceledRunRepo repo;
    @Autowired
    private TrainScheduleRepo trainScheduleRepo;

    @Override
    public Page<CanceledRun> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public CanceledRun getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public CanceledRun create(CanceledRun entity) {
        entity.setTrainSchedule(trainScheduleRepo.findById(entity.getTrainSchedule().getId()).get());
        return repo.save(entity);
    }

    @Override
    public CanceledRun update(Integer id, CanceledRun entity) {
        CanceledRun toUpdate = repo.getById(id);
        toUpdate.setDescription(entity.getDescription());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
