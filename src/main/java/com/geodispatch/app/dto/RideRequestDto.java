package com.geodispatch.app.dto;

import com.geodispatch.app.entities.enums.PaymentMethod;
import com.geodispatch.app.entities.enums.RideRequestStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class RideRequestDto {

    private Long id;
    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private PaymentMethod paymentMethod;
    private LocalDateTime requestedTime;
    private RiderDto rider;
    private Double fare;
    private RideRequestStatus rideRequestStatus;

    public RideRequestDto() {
    }

    public RideRequestDto(Long id,
                          PointDto pickupLocation,
                          PointDto dropOffLocation,
                          PaymentMethod paymentMethod,
                          LocalDateTime requestedTime,
                          RiderDto rider,
                          Double fare,
                          RideRequestStatus rideRequestStatus) {
        this.id = id;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.paymentMethod = paymentMethod;
        this.requestedTime = requestedTime;
        this.rider = rider;
        this.fare = fare;
        this.rideRequestStatus = rideRequestStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PointDto getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(PointDto pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public PointDto getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(PointDto dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(LocalDateTime requestedTime) {
        this.requestedTime = requestedTime;
    }

    public RiderDto getRider() {
        return rider;
    }

    public void setRider(RiderDto rider) {
        this.rider = rider;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public RideRequestStatus getRideRequestStatus() {
        return rideRequestStatus;
    }

    public void setRideRequestStatus(RideRequestStatus rideRequestStatus) {
        this.rideRequestStatus = rideRequestStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RideRequestDto)) {
            return false;
        }

        RideRequestDto other = (RideRequestDto) obj;

        return Objects.equals(id, other.id)
                && Objects.equals(pickupLocation, other.pickupLocation)
                && Objects.equals(dropOffLocation, other.dropOffLocation)
                && Objects.equals(paymentMethod, other.paymentMethod)
                && Objects.equals(requestedTime, other.requestedTime)
                && Objects.equals(rider, other.rider)
                && Objects.equals(fare, other.fare)
                && Objects.equals(rideRequestStatus, other.rideRequestStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                pickupLocation,
                dropOffLocation,
                paymentMethod,
                requestedTime,
                rider,
                fare,
                rideRequestStatus
        );
    }

    @Override
    public String toString() {
        return "RideRequestDto{" +
                "id=" + id +
                ", pickupLocation=" + pickupLocation +
                ", dropOffLocation=" + dropOffLocation +
                ", paymentMethod=" + paymentMethod +
                ", requestedTime=" + requestedTime +
                ", rider=" + rider +
                ", fare=" + fare +
                ", rideRequestStatus=" + rideRequestStatus +
                '}';
    }
}