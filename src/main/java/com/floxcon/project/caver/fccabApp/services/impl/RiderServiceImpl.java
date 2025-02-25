package com.floxcon.project.caver.fccabApp.services.impl;

import com.floxcon.project.caver.fccabApp.dto.DriverDto;
import com.floxcon.project.caver.fccabApp.dto.RideRequestDto;
import com.floxcon.project.caver.fccabApp.dto.RiderDto;
import com.floxcon.project.caver.fccabApp.services.RiderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {
    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        return null;
    }

    @Override
    public RiderDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RiderDto> getAllMyRides() {
        return List.of();
    }
}
