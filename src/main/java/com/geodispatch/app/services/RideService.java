package com.geodispatch.app.services;

import com.geodispatch.app.entities.Driver;
import com.geodispatch.app.entities.Ride;
import com.geodispatch.app.entities.RideRequest;
import com.geodispatch.app.entities.Rider;
import com.geodispatch.app.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {
  Ride getRideById(Long paramLong);
  
  Ride createNewRide(RideRequest paramRideRequest, Driver paramDriver);
  
  Ride updateRideStatus(Ride paramRide, RideStatus paramRideStatus);
  
  Page<Ride> getAllRidesOfRider(Rider paramRider, PageRequest paramPageRequest);
  
  Page<Ride> getAllRidesOfDriver(Driver paramDriver, PageRequest paramPageRequest);
}

