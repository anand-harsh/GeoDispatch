package com.geodispatch.app.controllers;

import com.geodispatch.app.dto.DriverDto;
import com.geodispatch.app.dto.RatingDto;
import com.geodispatch.app.dto.RideDto;
import com.geodispatch.app.dto.RideStartDto;
import com.geodispatch.app.dto.RiderDto;
import com.geodispatch.app.services.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
@Secured("ROLE_DRIVER")
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(
            @PathVariable Long rideRequestId) {

        RideDto ride = driverService.acceptRide(rideRequestId);

        return ResponseEntity.ok(ride);
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> startRide(
            @PathVariable Long rideRequestId,
            @Valid @RequestBody RideStartDto rideStartDto) {

        RideDto ride = driverService.startRide(
                rideRequestId,
                rideStartDto.getOtp()
        );

        return ResponseEntity.ok(ride);
    }

    @PostMapping("/endRide/{rideId}")
    public ResponseEntity<RideDto> endRide(
            @PathVariable Long rideId) {

        RideDto ride = driverService.endRide(rideId);

        return ResponseEntity.ok(ride);
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(
            @PathVariable Long rideId) {

        RideDto ride = driverService.cancelRide(rideId);

        return ResponseEntity.ok(ride);
    }

    @PostMapping("/rateRider")
    public ResponseEntity<RiderDto> rateRider(
            @Valid @RequestBody RatingDto ratingDto) {

        RiderDto rider = driverService.rateRider(
                ratingDto.getRideId(),
                ratingDto.getRating()
        );

        return ResponseEntity.ok(rider);
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<DriverDto> getMyProfile() {

        return ResponseEntity.ok(driverService.getMyProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<Page<RideDto>> getMyRides(
            @RequestParam(defaultValue = "0") Integer pageOffset,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        PageRequest pageable = PageRequest.of(
                pageOffset,
                pageSize,
                Sort.by(
                        Sort.Direction.DESC,
                        "createdTime",
                        "id"
                )
        );

        Page<RideDto> rides = driverService.getAllMyRides(pageable);

        return ResponseEntity.ok(rides);
    }
}