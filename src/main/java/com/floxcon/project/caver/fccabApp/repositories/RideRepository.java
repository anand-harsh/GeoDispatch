package com.floxcon.project.caver.fccabApp.repositories;

import com.floxcon.project.caver.fccabApp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
}
