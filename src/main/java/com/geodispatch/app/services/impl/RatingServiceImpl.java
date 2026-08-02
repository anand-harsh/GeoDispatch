 package com.geodispatch.app.services.impl;
 import com.geodispatch.app.dto.DriverDto;
 import com.geodispatch.app.dto.RiderDto;
 import com.geodispatch.app.entities.Driver;
 import com.geodispatch.app.entities.Rating;
 import com.geodispatch.app.entities.Ride;
 import com.geodispatch.app.entities.Rider;
 import com.geodispatch.app.exceptions.ResourceNotFoundException;
 import com.geodispatch.app.exceptions.RuntimeConflictException;
 import com.geodispatch.app.repositories.DriverRepository;
 import com.geodispatch.app.repositories.RatingRepository;
 import com.geodispatch.app.repositories.RiderRepository;
 import com.geodispatch.app.services.RatingService;
 import org.modelmapper.ModelMapper;
 import org.springframework.stereotype.Service;
 
 @Service
 public class RatingServiceImpl implements RatingService {
   public RatingServiceImpl(RatingRepository ratingRepository, DriverRepository driverRepository, RiderRepository riderRepository, ModelMapper modelMapper) {
/* 20 */     this.ratingRepository = ratingRepository; this.driverRepository = driverRepository; this.riderRepository = riderRepository; this.modelMapper = modelMapper;
   }
 
   
   private final RatingRepository ratingRepository;
   private final DriverRepository driverRepository;
   private final RiderRepository riderRepository;
   private final ModelMapper modelMapper;
   
   public DriverDto rateDriver(Ride ride, Integer rating) {
/* 30 */     Driver driver = ride.getDriver();
     
/* 32 */     Rating ratingObj = (Rating)this.ratingRepository.findByRide(ride).orElseThrow(() -> new ResourceNotFoundException("Rating not found for ride with id: " + ride.getId()));
     
/* 34 */     if (ratingObj.getDriverRating() != null) {
/* 35 */       throw new RuntimeConflictException("Driver has already been rated, cannot rate again");
     }
/* 37 */     ratingObj.setDriverRating(rating);
     
/* 39 */     this.ratingRepository.save(ratingObj);
     
/* 41 */     Double newRating = Double.valueOf(this.ratingRepository.findByDriver(driver)
/* 42 */         .stream()
/* 43 */         .mapToDouble(Rating::getDriverRating)
/* 44 */         .average().orElse(0.0D));
/* 45 */     driver.setRating(newRating);
     
/* 47 */     Driver savedDriver = (Driver)this.driverRepository.save(driver);
/* 48 */     return (DriverDto)this.modelMapper.map(savedDriver, DriverDto.class);
   }
 
   
   public RiderDto rateRider(Ride ride, Integer rating) {
/* 53 */     Rider rider = ride.getRider();
     
/* 55 */     Rating ratingObj = (Rating)this.ratingRepository.findByRide(ride).orElseThrow(() -> new ResourceNotFoundException("Rating not found for ride with id: " + ride.getId()));
/* 56 */     if (ratingObj.getRiderRating() != null) {
/* 57 */       throw new RuntimeConflictException("Rider has already been rated, cannot rate again");
     }
  ratingObj.setRiderRating(rating);
     
   this.ratingRepository.save(ratingObj);
     
   Double newRating = Double.valueOf(this.ratingRepository.findByRider(rider)
        .stream()
         .mapToDouble(Rating::getRiderRating)
        .average().orElse(0.0D));
    rider.setRating(newRating);
     
/* 69 */     Rider savedRider = (Rider)this.riderRepository.save(rider);
/* 70 */     return (RiderDto)this.modelMapper.map(savedRider, RiderDto.class);
   }
 
 
 
 
 
   
   public void createNewRating(Ride ride) {
   Rating rating = Rating.builder().rider(ride.getRider()).driver(ride.getDriver()).ride(ride).build();
     this.ratingRepository.save(rating);
   }
 }


