package com.floxcon.project.caver.fccabApp.strategies.impl;

import com.floxcon.project.caver.fccabApp.dto.RideRequestDto;
import com.floxcon.project.caver.fccabApp.entities.Driver;
import com.floxcon.project.caver.fccabApp.strategies.DriverMatchingStrategy;

import java.util.List;

public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
