package com.example.rzdwebapp.repository.crud;

import com.example.rzdwebapp.data.dto.fkListDto.IdEntityFK;
import com.example.rzdwebapp.data.dto.fkListDto.NamedEntityFK;
import com.example.rzdwebapp.data.entity.Staff;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepo extends JpaRepository<Staff,Integer> {
    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.NamedEntityFK(e.id, e.name) FROM Sector e")
    List<NamedEntityFK> getSectorList();
    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.IdEntityFK(e.id) FROM Brigade e")
    List<IdEntityFK> getBrigadeList();
    @Query(value = "SELECT new com.example.rzdwebapp.data.dto.fkListDto.IdEntityFK(e.id) FROM Train e")
    List<IdEntityFK> getTrainList();
    @Override
    @EntityGraph(attributePaths = {"specialization","sector"})
    Page<Staff> findAll(Pageable pageable);
}
