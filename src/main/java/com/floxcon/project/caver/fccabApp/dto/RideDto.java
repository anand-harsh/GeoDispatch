package com.floxcon.project.caver.fccabApp.dto;

import com.floxcon.project.caver.fccabApp.entities.Driver;
import com.floxcon.project.caver.fccabApp.entities.Rider;
import com.floxcon.project.caver.fccabApp.entities.enums.PaymentMethod;
import com.floxcon.project.caver.fccabApp.entities.enums.RideRequestStatus;
import com.floxcon.project.caver.fccabApp.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
    private Long id;
    private Point pickupLocation;
    private Point dropOffLocation;
    private LocalDateTime createdTime;
    private RiderDto rider;
    private Driver driver;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;
    private RideStatus rideStatus;
    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
