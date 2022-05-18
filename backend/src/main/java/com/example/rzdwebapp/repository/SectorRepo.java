package com.example.rzdwebapp.repository;

import com.example.rzdwebapp.data.dto.fkListDto.StaffFK;
import com.example.rzdwebapp.data.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface SectorRepo extends JpaRepository<Sector,Integer> {
    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.StaffFK(s.id, s.fullName) FROM Staff s where s.specialization.name = 'администрация'")
    List<StaffFK> getStaffList();


}
