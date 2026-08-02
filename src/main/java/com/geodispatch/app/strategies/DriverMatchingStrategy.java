package com.geodispatch.app.strategies;
import com.geodispatch.app.entities.Driver;
import com.geodispatch.app.entities.RideRequest;
import java.util.List;

public interface DriverMatchingStrategy {
  List<Driver> findMatchingDriver(RideRequest paramRideRequest);
}
