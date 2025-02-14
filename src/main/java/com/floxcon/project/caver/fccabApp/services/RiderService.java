package com.floxcon.project.caver.fccabApp.services;

import com.floxcon.project.caver.fccabApp.dto.DriverDto;
import com.floxcon.project.caver.fccabApp.dto.RideRequestDto;
import com.floxcon.project.caver.fccabApp.dto.RiderDto;
import com.floxcon.project.caver.fccabApp.entities.RideRequest;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);
    RiderDto cancelRide(Long rideId);
    DriverDto rateDriver(Long rideId, Integer rating);
    RiderDto getMyProfile();
    List<RiderDto> getAllMyRides();
}
