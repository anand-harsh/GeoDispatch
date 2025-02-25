package com.floxcon.project.caver.fccabApp.strategies.impl;

import com.floxcon.project.caver.fccabApp.dto.RideRequestDto;
import com.floxcon.project.caver.fccabApp.entities.Driver;
import com.floxcon.project.caver.fccabApp.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
