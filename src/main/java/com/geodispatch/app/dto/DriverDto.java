package com.geodispatch.app.dto;

import java.util.Objects;

public class DriverDto {

    private Long id;
    private UserDto user;
    private Double rating;
    private Boolean available;
    private String vehicleId;

    public DriverDto() {
    }

    public DriverDto(Long id, UserDto user, Double rating, Boolean available, String vehicleId) {
        this.id = id;
        this.user = user;
        this.rating = rating;
        this.available = available;
        this.vehicleId = vehicleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DriverDto)) return false;
        DriverDto other = (DriverDto) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(user, other.user)
                && Objects.equals(rating, other.rating)
                && Objects.equals(available, other.available)
                && Objects.equals(vehicleId, other.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, rating, available, vehicleId);
    }

    @Override
    public String toString() {
        return "DriverDto{" +
                "id=" + id +
                ", user=" + user +
                ", rating=" + rating +
                ", available=" + available +
                ", vehicleId='" + vehicleId + '\'' +
                '}';
    }
}