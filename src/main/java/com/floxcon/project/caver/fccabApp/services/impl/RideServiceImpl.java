package com.floxcon.project.caver.fccabApp.services.impl;

import com.floxcon.project.caver.fccabApp.dto.RideRequestDto;
import com.floxcon.project.caver.fccabApp.entities.Driver;
import com.floxcon.project.caver.fccabApp.entities.Ride;
import com.floxcon.project.caver.fccabApp.entities.enums.RideStatus;
import com.floxcon.project.caver.fccabApp.services.RideService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImpl implements RideService {
    @Override
    public Ride getRideById(Long rideId) {
        return null;
    }

    @Override
    public void matchWithDrivers(RideRequestDto rideRequestDto) {

    }

    @Override
    public Ride createNewRide(RideRequestDto rideRequestDto, Driver driver) {
        return null;
    }

    @Override
    public Ride updateRideStatus(Long rideId, RideStatus rideStatus) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }
}
