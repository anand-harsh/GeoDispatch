package com.geodispatch.app.services;

import com.geodispatch.app.entities.RideRequest;

public interface RideRequestService {
  RideRequest findRideRequestById(Long paramLong);
  
  void update(RideRequest paramRideRequest);
}

