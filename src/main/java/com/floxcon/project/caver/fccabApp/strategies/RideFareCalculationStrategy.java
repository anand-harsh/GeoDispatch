package com.floxcon.project.caver.fccabApp.strategies;

import com.floxcon.project.caver.fccabApp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDto rideRequestDto);
}
