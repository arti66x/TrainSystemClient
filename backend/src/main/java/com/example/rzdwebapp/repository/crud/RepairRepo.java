package com.example.rzdwebapp.repository.crud;

import com.example.rzdwebapp.data.dto.fkListDto.IdEntityFK;
import com.example.rzdwebapp.data.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepo extends JpaRepository<Repair,Integer> {
    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.IdEntityFK(e.id) " +
            "FROM Brigade e where e.type = com.example.rzdwebapp.data.entity.BrigadeType.REPAIR_BRIGADE")
    List<IdEntityFK> getRepairBrigadeList();

    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.IdEntityFK(e.id) FROM Train e")
    List<IdEntityFK> getTrainList();
}
