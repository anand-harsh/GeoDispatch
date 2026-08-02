package com.geodispatch.app.entities;

public class RatingBuilder {

    private Long id;
    private Ride ride;
    private Rider rider;
    private Driver driver;
    private Integer driverRating;
    private Integer riderRating;

    public RatingBuilder() {
    }

    public RatingBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public RatingBuilder ride(Ride ride) {
        this.ride = ride;
        return this;
    }

    public RatingBuilder rider(Rider rider) {
        this.rider = rider;
        return this;
    }

    public RatingBuilder driver(Driver driver) {
        this.driver = driver;
        return this;
    }

    public RatingBuilder driverRating(Integer driverRating) {
        this.driverRating = driverRating;
        return this;
    }

    public RatingBuilder riderRating(Integer riderRating) {
        this.riderRating = riderRating;
        return this;
    }

    public Rating build() {
        return new Rating(
                id,
                ride,
                rider,
                driver,
                driverRating,
                riderRating
        );
    }

    @Override
    public String toString() {
        return "RatingBuilder{" +
                "id=" + id +
                ", ride=" + ride +
                ", rider=" + rider +
                ", driver=" + driver +
                ", driverRating=" + driverRating +
                ", riderRating=" + riderRating +
                '}';
    }
}