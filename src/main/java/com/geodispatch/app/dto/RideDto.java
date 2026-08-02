package com.geodispatch.app.dto;

import com.geodispatch.app.entities.enums.PaymentMethod;
import com.geodispatch.app.entities.enums.RideStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class RideDto {

    private Long id;
    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private LocalDateTime createdTime;
    private RiderDto rider;
    private DriverDto driver;
    private PaymentMethod paymentMethod;
    private RideStatus rideStatus;
    private String otp;
    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public RideDto() {
    }

    public RideDto(Long id,
                   PointDto pickupLocation,
                   PointDto dropOffLocation,
                   LocalDateTime createdTime,
                   RiderDto rider,
                   DriverDto driver,
                   PaymentMethod paymentMethod,
                   RideStatus rideStatus,
                   String otp,
                   Double fare,
                   LocalDateTime startedAt,
                   LocalDateTime endedAt) {

        this.id = id;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.createdTime = createdTime;
        this.rider = rider;
        this.driver = driver;
        this.paymentMethod = paymentMethod;
        this.rideStatus = rideStatus;
        this.otp = otp;
        this.fare = fare;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
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

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public RiderDto getRider() {
        return rider;
    }

    public void setRider(RiderDto rider) {
        this.rider = rider;
    }

    public DriverDto getDriver() {
        return driver;
    }

    public void setDriver(DriverDto driver) {
        this.driver = driver;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RideDto)) {
            return false;
        }

        RideDto other = (RideDto) obj;

        return Objects.equals(id, other.id)
                && Objects.equals(pickupLocation, other.pickupLocation)
                && Objects.equals(dropOffLocation, other.dropOffLocation)
                && Objects.equals(createdTime, other.createdTime)
                && Objects.equals(rider, other.rider)
                && Objects.equals(driver, other.driver)
                && Objects.equals(paymentMethod, other.paymentMethod)
                && Objects.equals(rideStatus, other.rideStatus)
                && Objects.equals(otp, other.otp)
                && Objects.equals(fare, other.fare)
                && Objects.equals(startedAt, other.startedAt)
                && Objects.equals(endedAt, other.endedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                pickupLocation,
                dropOffLocation,
                createdTime,
                rider,
                driver,
                paymentMethod,
                rideStatus,
                otp,
                fare,
                startedAt,
                endedAt
        );
    }

    @Override
    public String toString() {
        return "RideDto{" +
                "id=" + id +
                ", pickupLocation=" + pickupLocation +
                ", dropOffLocation=" + dropOffLocation +
                ", createdTime=" + createdTime +
                ", rider=" + rider +
                ", driver=" + driver +
                ", paymentMethod=" + paymentMethod +
                ", rideStatus=" + rideStatus +
                ", otp='" + otp + '\'' +
                ", fare=" + fare +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                '}';
    }
}