package com.example.rzdwebapp.repository.crud;

import com.example.rzdwebapp.data.dto.fkListDto.NamedEntityFK;
import com.example.rzdwebapp.data.dto.fkListDto.StationFK;
import com.example.rzdwebapp.data.entity.RouteStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteStationRepo extends JpaRepository<RouteStation,Integer> {
    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.NamedEntityFK(e.id, e.name) FROM Route e")
    List<NamedEntityFK> getRouteList();

    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.StationFK(e.id, e.city) FROM Station e")
    List<StationFK> getStationList();
}
