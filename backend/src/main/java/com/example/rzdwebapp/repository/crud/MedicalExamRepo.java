package com.example.rzdwebapp.repository.crud;

import com.example.rzdwebapp.data.dto.fkListDto.StaffFK;
import com.example.rzdwebapp.data.entity.MedicalExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalExamRepo extends JpaRepository<MedicalExam,Integer> {
    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.StaffFK(s.id, s.fullName) FROM Staff s where s.specialization.name = 'водитель поезда'")
    List<StaffFK> getStaffList();
}
