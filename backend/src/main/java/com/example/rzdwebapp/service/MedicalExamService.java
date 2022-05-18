package com.example.rzdwebapp.service;

import com.example.rzdwebapp.data.entity.MedicalExam;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class MedicalExamService implements CrudService<MedicalExam,Integer>{

    @Override
    public Page<MedicalExam> findAllPaged(Integer page, Integer rowsPerPage) {
        return null;
    }

    @Override
    public MedicalExam getById(Integer id) {
        return null;
    }

    @Override
    public MedicalExam create(MedicalExam medicalExam) {
        return null;
    }

    @Override
    public MedicalExam update(Integer id, MedicalExam medicalExam) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
