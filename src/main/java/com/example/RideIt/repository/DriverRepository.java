package com.example.RideIt.repository;

import com.example.RideIt.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriverRepository extends JpaRepository<Driver,Integer> {

    @Query(value = "Select d from Driver d where d.mobNo=:mobNo")
    Driver getDriverByMob(String mobNo);

    Driver findDriverByMobNo(String mobNo);

    @Query(value = "select c.id from Driver c where mobNo=:mobNo")
    int getIdByMobNo(String mobNo);
}
