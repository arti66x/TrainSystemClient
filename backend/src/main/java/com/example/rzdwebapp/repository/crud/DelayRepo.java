package com.example.rzdwebapp.repository.crud;

import com.example.rzdwebapp.data.dto.fkListDto.RouteStationFK;
import com.example.rzdwebapp.data.entity.Delay;
import com.example.rzdwebapp.data.entity.DelayId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DelayRepo extends JpaRepository<Delay, DelayId> {
    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.RouteStationFK(rs.id,r.name,s.city) FROM RouteStation rs inner join rs.station s inner join rs.route r")
    List<RouteStationFK> getStationList();
}
