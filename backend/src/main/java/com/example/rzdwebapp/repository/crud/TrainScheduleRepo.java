package com.example.rzdwebapp.repository.crud;

import com.example.rzdwebapp.data.dto.fkListDto.IdEntityFK;
import com.example.rzdwebapp.data.dto.fkListDto.NamedEntityFK;
import com.example.rzdwebapp.data.entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainScheduleRepo extends JpaRepository<TrainSchedule,Integer> {
    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.NamedEntityFK(e.id, e.name) FROM Route e")
    List<NamedEntityFK> getRouteList();

    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.IdEntityFK(e.id) FROM Train e")
    List<IdEntityFK> getTrainList();
}
