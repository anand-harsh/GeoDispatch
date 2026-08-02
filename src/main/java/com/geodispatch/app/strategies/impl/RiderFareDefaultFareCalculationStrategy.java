package com.geodispatch.app.strategies.impl;
import org.springframework.stereotype.Service;

import com.geodispatch.app.entities.RideRequest;
import com.geodispatch.app.services.DistanceService;
import com.geodispatch.app.strategies.RideFareCalculationStrategy;

@Service
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
   public RiderFareDefaultFareCalculationStrategy(DistanceService distanceService) {
      this.distanceService = distanceService;
   }

   private final DistanceService distanceService;

   public double calculateFare(RideRequest rideRequest) {
      double distance = this.distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest
            .getDropOffLocation());
      return distance * 10.0D;
   }
}
