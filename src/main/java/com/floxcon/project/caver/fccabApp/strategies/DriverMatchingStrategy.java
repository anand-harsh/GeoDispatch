package com.floxcon.project.caver.fccabApp.strategies;

import com.floxcon.project.caver.fccabApp.dto.RideRequestDto;
import com.floxcon.project.caver.fccabApp.entities.Driver;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequestDto rideRequestDto);
}
