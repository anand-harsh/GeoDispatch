package com.geodispatch.app.strategies.impl;
import com.geodispatch.app.entities.Driver;
import com.geodispatch.app.entities.RideRequest;
import com.geodispatch.app.repositories.DriverRepository;
import com.geodispatch.app.strategies.DriverMatchingStrategy;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
   public DriverMatchingHighestRatedDriverStrategy(DriverRepository driverRepository) {
      this.driverRepository = driverRepository;
   }

   private final DriverRepository driverRepository;

   public List<Driver> findMatchingDriver(RideRequest rideRequest) {
      return this.driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
   }
}
