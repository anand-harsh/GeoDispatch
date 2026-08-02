package com.geodispatch.app.strategies.impl;
import com.geodispatch.app.entities.RideRequest;
import com.geodispatch.app.services.DistanceService;
import com.geodispatch.app.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
   public RideFareSurgePricingFareCalculationStrategy(DistanceService distanceService) {
      this.distanceService = distanceService;
   }

   private final DistanceService distanceService;
   private static final double SURGE_FACTOR = 2.0D;

   public double calculateFare(RideRequest rideRequest) {
      double distance = this.distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest
            .getDropOffLocation());
      return distance * 10.0D * 2.0D;
   }
}
