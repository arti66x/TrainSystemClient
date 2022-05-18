package com.example.rzdwebapp.repository;

import com.example.rzdwebapp.data.entity.Brigade;
import com.example.rzdwebapp.data.entity.CanceledRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CanceledRunRepo extends JpaRepository<CanceledRun,Integer> {
}
