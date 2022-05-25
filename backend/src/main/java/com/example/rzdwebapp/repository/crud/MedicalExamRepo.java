package com.example.rzdwebapp.repository.crud;

import com.example.rzdwebapp.data.entity.MedicalExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalExamRepo extends JpaRepository<MedicalExam,Integer> {

}
