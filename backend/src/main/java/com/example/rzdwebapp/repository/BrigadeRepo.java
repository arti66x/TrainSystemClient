package com.example.rzdwebapp.repository;

import com.example.rzdwebapp.data.dto.fkListDto.NamedEntityFK;
import com.example.rzdwebapp.data.entity.Brigade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrigadeRepo extends JpaRepository<Brigade,Integer> {
    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.NamedEntityFK(e.id, e.name) FROM Sector e")
    List<NamedEntityFK> getSectorList();
}
