package com.geodispatch.app.controllers;

import com.geodispatch.app.dto.DriverDto;
import com.geodispatch.app.dto.RatingDto;
import com.geodispatch.app.dto.RideDto;
import com.geodispatch.app.dto.RideRequestDto;
import com.geodispatch.app.dto.RiderDto;
import com.geodispatch.app.services.RiderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/riders")
@RequiredArgsConstructor
@Secured("ROLE_RIDER")
public class RiderController {

    private final RiderService riderService;

    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(
            @Valid @RequestBody RideRequestDto rideRequestDto) {

        RideRequestDto rideRequest = riderService.requestRide(rideRequestDto);

        return ResponseEntity.ok(rideRequest);
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(
            @PathVariable Long rideId) {

        RideDto ride = riderService.cancelRide(rideId);

        return ResponseEntity.ok(ride);
    }

    @PostMapping("/rateDriver")
    public ResponseEntity<DriverDto> rateDriver(
            @Valid @RequestBody RatingDto ratingDto) {

        DriverDto driver = riderService.rateDriver(
                ratingDto.getRideId(),
                ratingDto.getRating()
        );

        return ResponseEntity.ok(driver);
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<RiderDto> getMyProfile() {

        return ResponseEntity.ok(riderService.getMyProfile());
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

        Page<RideDto> rides = riderService.getAllMyRides(pageable);

        return ResponseEntity.ok(rides);
    }
}