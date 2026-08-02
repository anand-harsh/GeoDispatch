package com.geodispatch.app.repositories;

import com.geodispatch.app.entities.Driver;
import com.geodispatch.app.entities.Rating;
import com.geodispatch.app.entities.Ride;
import com.geodispatch.app.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByRider(Rider rider);

    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);

}