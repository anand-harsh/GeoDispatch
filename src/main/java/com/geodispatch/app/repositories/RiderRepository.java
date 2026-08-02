package com.geodispatch.app.repositories;

import com.geodispatch.app.entities.Rider;
import com.geodispatch.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

    Optional<Rider> findByUser(User user);

}