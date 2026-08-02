package com.geodispatch.app.strategies.impl;
import com.geodispatch.app.entities.Driver;
import com.geodispatch.app.entities.RideRequest;
import com.geodispatch.app.repositories.DriverRepository;
import com.geodispatch.app.strategies.DriverMatchingStrategy;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
   public DriverMatchingNearestDriverStrategy(DriverRepository driverRepository) {
      this.driverRepository = driverRepository;
   }

   private final DriverRepository driverRepository;

   public List<Driver> findMatchingDriver(RideRequest rideRequest) {
      return this.driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
   }
}
