package com.floxcon.project.caver.fccabApp.repositories;

import com.floxcon.project.caver.fccabApp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
}
