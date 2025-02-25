package com.floxcon.project.caver.fccabApp.services.impl;

import com.floxcon.project.caver.fccabApp.dto.DriverDto;
import com.floxcon.project.caver.fccabApp.dto.RiderDto;
import com.floxcon.project.caver.fccabApp.services.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Override
    public RiderDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto startRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RiderDto> getAllMyRides() {
        return List.of();
    }
}
