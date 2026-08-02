package com.geodispatch.app.strategies;
import com.geodispatch.app.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.geodispatch.app.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.geodispatch.app.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import com.geodispatch.app.strategies.impl.RiderFareDefaultFareCalculationStrategy;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

@Component
public class RideStrategyManager {
   public RideStrategyManager(DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy,
         DriverMatchingNearestDriverStrategy nearestDriverStrategy,
         RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy,
         RiderFareDefaultFareCalculationStrategy defaultFareCalculationStrategy) {
      this.highestRatedDriverStrategy = highestRatedDriverStrategy;
      this.nearestDriverStrategy = nearestDriverStrategy;
      this.surgePricingFareCalculationStrategy = surgePricingFareCalculationStrategy;
      this.defaultFareCalculationStrategy = defaultFareCalculationStrategy;
   }

   private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
   private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
   private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
   private final RiderFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;

   public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
      if (riderRating >= 4.8D) {
         return (DriverMatchingStrategy) this.highestRatedDriverStrategy;
      }
      return (DriverMatchingStrategy) this.nearestDriverStrategy;
   }

   public RideFareCalculationStrategy rideFareCalculationStrategy() {
      LocalTime surgeStartTime = LocalTime.of(18, 0);
      LocalTime surgeEndTime = LocalTime.of(21, 0);
      LocalTime currentTime = LocalTime.now();

      boolean isSurgeTime = (currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime));

      if (isSurgeTime) {
         return (RideFareCalculationStrategy) this.surgePricingFareCalculationStrategy;
      }
      return (RideFareCalculationStrategy) this.defaultFareCalculationStrategy;
   }
}
