package com.geodispatch.app.services;

import com.geodispatch.app.dto.DriverDto;
import com.geodispatch.app.dto.RiderDto;
import com.geodispatch.app.entities.Ride;

public interface RatingService {
  DriverDto rateDriver(Ride paramRide, Integer paramInteger);
  
  RiderDto rateRider(Ride paramRide, Integer paramInteger);
  
  void createNewRating(Ride paramRide);
}


