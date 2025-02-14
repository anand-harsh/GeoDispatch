package com.floxcon.project.caver.fccabApp.services;

import com.floxcon.project.caver.fccabApp.dto.DriverDto;
import com.floxcon.project.caver.fccabApp.dto.RiderDto;

import java.util.List;

public interface DriverService {
    RiderDto cancelRide(Long rideId);
    RiderDto startRide(Long rideId);
    RiderDto endRide(Long rideId);
    RiderDto rateRider(Long rideId, Integer rating);
    DriverDto getMyProfile();
    List<RiderDto> getAllMyRides();
}
