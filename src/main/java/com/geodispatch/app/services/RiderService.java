package com.geodispatch.app.services;

import com.geodispatch.app.dto.DriverDto;
import com.geodispatch.app.dto.RideDto;
import com.geodispatch.app.dto.RideRequestDto;
import com.geodispatch.app.dto.RiderDto;
import com.geodispatch.app.entities.Rider;
import com.geodispatch.app.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {
  RideRequestDto requestRide(RideRequestDto paramRideRequestDto);
  
  RideDto cancelRide(Long paramLong);
  
  DriverDto rateDriver(Long paramLong, Integer paramInteger);
  
  RiderDto getMyProfile();
  
  Page<RideDto> getAllMyRides(PageRequest paramPageRequest);
  
  Rider createNewRider(User paramUser);
  
  Rider getCurrentRider();
}
