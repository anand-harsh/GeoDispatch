package com.geodispatch.app.services.impl;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.geodispatch.app.entities.Driver;
import com.geodispatch.app.entities.Ride;
import com.geodispatch.app.entities.RideRequest;
import com.geodispatch.app.entities.Rider;
import com.geodispatch.app.entities.enums.RideRequestStatus;
import com.geodispatch.app.entities.enums.RideStatus;
import com.geodispatch.app.exceptions.ResourceNotFoundException;
import com.geodispatch.app.repositories.RideRepository;
import com.geodispatch.app.services.RideRequestService;
import com.geodispatch.app.services.RideService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ride not found with id: " + rideId));
    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {

        // Mark RideRequest as accepted
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

        // Convert RideRequest -> Ride
        Ride ride = modelMapper.map(rideRequest, Ride.class);

        ride.setId(null);
        ride.setDriver(driver);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setOtp(generateRandomOTP());

        // Update RideRequest status
        rideRequestService.update(rideRequest);

        // Save Ride
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {

        ride.setRideStatus(rideStatus);

        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
        return rideRepository.findByRider(rider, pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver, pageRequest);
    }

    /**
     * Generates a random 4-digit OTP.
     * Example: 0007, 0423, 9812
     */
    private String generateRandomOTP() {
        Random random = new Random();
        int otp = random.nextInt(10000);
        return String.format("%04d", otp);
    }
}