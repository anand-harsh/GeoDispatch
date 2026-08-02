package com.geodispatch.app.services.impl;

import com.geodispatch.app.dto.DriverDto;
import com.geodispatch.app.dto.RideDto;
import com.geodispatch.app.dto.RiderDto;
import com.geodispatch.app.entities.Driver;
import com.geodispatch.app.entities.Ride;
import com.geodispatch.app.entities.RideRequest;
import com.geodispatch.app.entities.User;
import com.geodispatch.app.entities.enums.RideRequestStatus;
import com.geodispatch.app.entities.enums.RideStatus;
import com.geodispatch.app.exceptions.ResourceNotFoundException;
import com.geodispatch.app.repositories.DriverRepository;
import com.geodispatch.app.services.DriverService;
import com.geodispatch.app.services.PaymentService;
import com.geodispatch.app.services.RatingService;
import com.geodispatch.app.services.RideRequestService;
import com.geodispatch.app.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    private final RatingService ratingService;

    @Override
    public RideDto acceptRide(Long rideRequestId) {

        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

        if (rideRequest.getRideRequestStatus() != RideRequestStatus.PENDING) {
            throw new RuntimeException(
                    "Ride request cannot be accepted. Current status: "
                            + rideRequest.getRideRequestStatus()
            );
        }

        Driver currentDriver = getCurrentDriver();

        if (!currentDriver.getAvailable()) {
            throw new RuntimeException("Driver is currently unavailable.");
        }

        Driver updatedDriver = updateDriverAvailability(currentDriver, false);

        Ride ride = rideService.createNewRide(rideRequest, updatedDriver);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);

        Driver driver = getCurrentDriver();

        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException(
                    "Only the assigned driver can cancel this ride."
            );
        }

        if (ride.getRideStatus() != RideStatus.CONFIRMED) {
            throw new RuntimeException(
                    "Ride cannot be cancelled. Current status: "
                            + ride.getRideStatus()
            );
        }

        rideService.updateRideStatus(ride, RideStatus.CANCELLED);

        updateDriverAvailability(driver, true);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto startRide(Long rideId, String otp) {

        Ride ride = rideService.getRideById(rideId);

        Driver driver = getCurrentDriver();

        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException(
                    "Only the assigned driver can start this ride."
            );
        }

        if (ride.getRideStatus() != RideStatus.CONFIRMED) {
            throw new RuntimeException(
                    "Ride must be in CONFIRMED state to start."
            );
        }

        if (!ride.getOtp().equals(otp)) {
            throw new RuntimeException("Invalid OTP.");
        }

        ride.setStartedAt(LocalDateTime.now());

        Ride updatedRide = rideService.updateRideStatus(
                ride,
                RideStatus.ONGOING
        );

        paymentService.createNewPayment(updatedRide);

        ratingService.createNewRating(updatedRide);

        return modelMapper.map(updatedRide, RideDto.class);
    }

        @Override
    public RideDto endRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);

        Driver driver = getCurrentDriver();

        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException(
                    "Only the assigned driver can end this ride."
            );
        }

        if (ride.getRideStatus() != RideStatus.ONGOING) {
            throw new RuntimeException(
                    "Ride must be in ONGOING state to end."
            );
        }

        ride.setEndedAt(LocalDateTime.now());

        Ride updatedRide = rideService.updateRideStatus(
                ride,
                RideStatus.ENDED
        );

        updateDriverAvailability(driver, true);

        paymentService.processPayment(updatedRide);

        return modelMapper.map(updatedRide, RideDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {

        Ride ride = rideService.getRideById(rideId);

        Driver driver = getCurrentDriver();

        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException(
                    "Only the assigned driver can rate the rider."
            );
        }

        if (ride.getRideStatus() != RideStatus.ENDED) {
            throw new RuntimeException(
                    "Ride must be completed before rating."
            );
        }

        return ratingService.rateRider(ride, rating);
    }

    @Override
    @Transactional(readOnly = true)
    public DriverDto getMyProfile() {

        Driver driver = getCurrentDriver();

        return modelMapper.map(driver, DriverDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {

        Driver driver = getCurrentDriver();

        return rideService
                .getAllRidesOfDriver(driver, pageRequest)
                .map(ride -> modelMapper.map(ride, RideDto.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Driver getCurrentDriver() {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return driverRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Driver not found for user id: " + user.getId()
                        ));
    }

    @Override
    public Driver updateDriverAvailability(
            Driver driver,
            boolean available) {

        driver.setAvailable(available);

        return driverRepository.save(driver);
    }

    @Override
    public Driver createNewDriver(Driver driver) {

        return driverRepository.save(driver);
    }
}