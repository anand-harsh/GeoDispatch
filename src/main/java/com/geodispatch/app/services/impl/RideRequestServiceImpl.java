package com.geodispatch.app.services.impl;

import com.geodispatch.app.entities.RideRequest;
import com.geodispatch.app.exceptions.ResourceNotFoundException;
import com.geodispatch.app.repositories.RideRequestRepository;
import com.geodispatch.app.services.RideRequestService;
import org.springframework.stereotype.Service;

@Service
public class RideRequestServiceImpl implements RideRequestService {
   public RideRequestServiceImpl(RideRequestRepository rideRequestRepository) {
      this.rideRequestRepository = rideRequestRepository;
   }

   private final RideRequestRepository rideRequestRepository;

   public RideRequest findRideRequestById(Long rideRequestId) {
      return (RideRequest) this.rideRequestRepository.findById(rideRequestId)
            .orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id: " + rideRequestId));
   }

   public void update(RideRequest rideRequest) {
      this.rideRequestRepository.findById(rideRequest.getId())
            .orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id: " + rideRequest.getId()));
      this.rideRequestRepository.save(rideRequest);
   }
}
