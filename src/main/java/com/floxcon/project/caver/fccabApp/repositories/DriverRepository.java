package com.floxcon.project.caver.fccabApp.repositories;

import com.floxcon.project.caver.fccabApp.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
