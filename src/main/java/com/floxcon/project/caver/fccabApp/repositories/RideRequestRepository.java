package com.floxcon.project.caver.fccabApp.repositories;

import com.floxcon.project.caver.fccabApp.entities.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
}
