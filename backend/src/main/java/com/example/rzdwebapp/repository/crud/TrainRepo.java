package com.example.rzdwebapp.repository.crud;

import com.example.rzdwebapp.data.dto.fkListDto.IdEntityFK;
import com.example.rzdwebapp.data.dto.fkListDto.StationFK;
import com.example.rzdwebapp.data.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepo extends JpaRepository<Train,Integer> {
    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.IdEntityFK(e.id) " +
            "FROM Brigade e where e.type = com.example.rzdwebapp.data.entity.BrigadeType.REPAIR_BRIGADE")
    List<IdEntityFK> getRepairBrigadeList();

    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.IdEntityFK(e.id) " +
            "FROM Brigade e where e.type = com.example.rzdwebapp.data.entity.BrigadeType.LOCOMOTIVE_BRIGADE")
    List<IdEntityFK> getTrainBrigadeList();

    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.StationFK(e.id,e.city) FROM Station e")
    List<StationFK> getStationList();
}
