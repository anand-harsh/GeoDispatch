package com.geodispatch.app.services;

import com.geodispatch.app.dto.DriverDto;
import com.geodispatch.app.dto.RideDto;
import com.geodispatch.app.dto.RiderDto;
import com.geodispatch.app.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {
  RideDto acceptRide(Long paramLong);
  
  RideDto cancelRide(Long paramLong);
  
  RideDto startRide(Long paramLong, String paramString);
  
  RideDto endRide(Long paramLong);
  
  RiderDto rateRider(Long paramLong, Integer paramInteger);
  
  DriverDto getMyProfile();
  
  Page<RideDto> getAllMyRides(PageRequest paramPageRequest);
  
  Driver getCurrentDriver();
  
  Driver updateDriverAvailability(Driver paramDriver, boolean paramBoolean);
  
  Driver createNewDriver(Driver paramDriver);
}
