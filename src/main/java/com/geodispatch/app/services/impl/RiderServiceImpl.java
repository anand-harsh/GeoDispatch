package com.geodispatch.app.services.impl;

import com.geodispatch.app.dto.DriverDto;
import com.geodispatch.app.dto.RideDto;
import com.geodispatch.app.dto.RideRequestDto;
import com.geodispatch.app.dto.RiderDto;
import com.geodispatch.app.entities.Driver;
import com.geodispatch.app.entities.Ride;
import com.geodispatch.app.entities.RideRequest;
import com.geodispatch.app.entities.Rider;
import com.geodispatch.app.entities.User;
import com.geodispatch.app.entities.enums.RideRequestStatus;
import com.geodispatch.app.entities.enums.RideStatus;
import com.geodispatch.app.exceptions.ResourceNotFoundException;
import com.geodispatch.app.repositories.RideRequestRepository;
import com.geodispatch.app.repositories.RiderRepository;
import com.geodispatch.app.services.DriverService;
import com.geodispatch.app.services.RatingService;
import com.geodispatch.app.services.RideService;
import com.geodispatch.app.services.RiderService;
import com.geodispatch.app.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {

        Rider rider = getCurrentRider();

        RideRequest rideRequest =
                modelMapper.map(rideRequestDto, RideRequest.class);

        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        double fare = rideStrategyManager
                .rideFareCalculationStrategy()
                .calculateFare(rideRequest);

        rideRequest.setFare(fare);

        RideRequest savedRideRequest =
                rideRequestRepository.save(rideRequest);

        List<Driver> matchedDrivers =
                rideStrategyManager
                        .driverMatchingStrategy(rider.getRating())
                        .findMatchingDriver(savedRideRequest);

        log.info("Matched {} drivers for ride request {}",
                matchedDrivers.size(),
                savedRideRequest.getId());

        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {

        Rider rider = getCurrentRider();

        Ride ride = rideService.getRideById(rideId);

        if (!rider.equals(ride.getRider())) {
            throw new RuntimeException(
                    "You are not the owner of this ride."
            );
        }

        if (ride.getRideStatus() != RideStatus.CONFIRMED) {
            throw new RuntimeException(
                    "Only confirmed rides can be cancelled."
            );
        }

        Ride cancelledRide =
                rideService.updateRideStatus(
                        ride,
                        RideStatus.CANCELLED
                );

        driverService.updateDriverAvailability(
                ride.getDriver(),
                true
        );

        return modelMapper.map(cancelledRide, RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {

        Ride ride = rideService.getRideById(rideId);

        Rider rider = getCurrentRider();

        if (!rider.equals(ride.getRider())) {
            throw new RuntimeException(
                    "You are not allowed to rate this driver."
            );
        }

        if (ride.getRideStatus() != RideStatus.ENDED) {
            throw new RuntimeException(
                    "Ride must be completed before rating."
            );
        }

        return ratingService.rateDriver(ride, rating);
    }
        @Override
    @Transactional(readOnly = true)
    public RiderDto getMyProfile() {

        Rider rider = getCurrentRider();

        return modelMapper.map(rider, RiderDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {

        Rider rider = getCurrentRider();

        return rideService
                .getAllRidesOfRider(rider, pageRequest)
                .map(ride -> modelMapper.map(ride, RideDto.class));
    }

    @Override
    public Rider createNewRider(User user) {

        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();

        return riderRepository.save(rider);
    }

    @Override
    @Transactional(readOnly = true)
    public Rider getCurrentRider() {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return riderRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Rider not found for user id: " + user.getId()
                        ));
    }
}