package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.Staff;
import com.example.rzdwebapp.repository.SectorRepo;
import com.example.rzdwebapp.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StaffService implements CrudService<Staff,Integer> {
    @Autowired
    private StaffRepo repo;

    @Override
    public Page<Staff> findAllPaged(Integer page, Integer size) {
        return repo.findAll(PageRequest.of(page,size, Sort.by("id")));
    }

    @Override
    public Staff getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public Staff create(Staff entity) {
        return repo.save(entity);
    }

    @Override
    public Staff update(Integer id, Staff entity) {
        Staff toUpdate = repo.getById(id);
        toUpdate.setBirthdate(entity.getBirthdate());
        toUpdate.setBrigade(entity.getBrigade());
        toUpdate.setFullName(entity.getFullName());
        toUpdate.setHireDate(entity.getHireDate());
        toUpdate.setIsMale(entity.getIsMale());
        toUpdate.setSpecialization(entity.getSpecialization());
        toUpdate.setSalary(entity.getSalary());
        toUpdate.setSector(entity.getSector());
        return repo.save(toUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
