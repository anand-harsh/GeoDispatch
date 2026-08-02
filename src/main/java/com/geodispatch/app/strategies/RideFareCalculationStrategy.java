package com.geodispatch.app.strategies;

import com.geodispatch.app.entities.RideRequest;

public interface RideFareCalculationStrategy {
  public static final double RIDE_FARE_MULTIPLIER = 10.0D;

  double calculateFare(RideRequest paramRideRequest);
}
