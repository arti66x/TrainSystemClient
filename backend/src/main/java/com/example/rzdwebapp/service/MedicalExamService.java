package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.Delay;
import com.example.rzdwebapp.data.entity.DelayId;
import com.example.rzdwebapp.data.entity.MedicalExam;
import com.example.rzdwebapp.repository.crud.DelayRepo;
import com.example.rzdwebapp.repository.crud.MedicalExamRepo;
import com.example.rzdwebapp.repository.crud.RouteStationRepo;
import com.example.rzdwebapp.repository.crud.TrainScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MedicalExamService implements CrudService<MedicalExam, Integer>{
    @Autowired
    private MedicalExamRepo repo;


    @Override
    public Page<MedicalExam> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public MedicalExam getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public MedicalExam create(MedicalExam entity) {
        return repo.save(entity);
    }

    @Override
    public MedicalExam update(Integer id, MedicalExam entity) {
        MedicalExam toUpdate = repo.getById(id);
        toUpdate.setDate(entity.getDate());
        toUpdate.setStaff(entity.getStaff());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
