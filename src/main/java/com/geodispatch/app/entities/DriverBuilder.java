package com.geodispatch.app.entities;

import org.locationtech.jts.geom.Point;

public class DriverBuilder {

    private Long id;
    private User user;
    private Double rating;
    private Boolean available;
    private String vehicleId;
    private Point currentLocation;

    public DriverBuilder() {
    }

    public DriverBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public DriverBuilder user(User user) {
        this.user = user;
        return this;
    }

    public DriverBuilder rating(Double rating) {
        this.rating = rating;
        return this;
    }

    public DriverBuilder available(Boolean available) {
        this.available = available;
        return this;
    }

    public DriverBuilder vehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public DriverBuilder currentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
        return this;
    }

    public Driver build() {
        return new Driver(
                id,
                user,
                rating,
                available,
                vehicleId,
                currentLocation
        );
    }

    @Override
    public String toString() {
        return "DriverBuilder{" +
                "id=" + id +
                ", user=" + user +
                ", rating=" + rating +
                ", available=" + available +
                ", vehicleId='" + vehicleId + '\'' +
                ", currentLocation=" + currentLocation +
                '}';
    }
}