package com.geodispatch.app.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(indexes = {
        @Index(name = "idx_rating_rider", columnList = "rider_id"),
        @Index(name = "idx_rating_driver", columnList = "driver_id")
})
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Ride ride;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private Driver driver;

    private Integer driverRating;

    private Integer riderRating;

    public Rating() {
    }

    public Rating(Long id,
                  Ride ride,
                  Rider rider,
                  Driver driver,
                  Integer driverRating,
                  Integer riderRating) {
        this.id = id;
        this.ride = ride;
        this.rider = rider;
        this.driver = driver;
        this.driverRating = driverRating;
        this.riderRating = riderRating;
    }

    public static RatingBuilder builder() {
        return new RatingBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Integer getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(Integer driverRating) {
        this.driverRating = driverRating;
    }

    public Integer getRiderRating() {
        return riderRating;
    }

    public void setRiderRating(Integer riderRating) {
        this.riderRating = riderRating;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Rating)) {
            return false;
        }

        Rating other = (Rating) obj;

        return Objects.equals(id, other.id)
                && Objects.equals(ride, other.ride)
                && Objects.equals(rider, other.rider)
                && Objects.equals(driver, other.driver)
                && Objects.equals(driverRating, other.driverRating)
                && Objects.equals(riderRating, other.riderRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
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
        return "Rating{" +
                "id=" + id +
                ", ride=" + ride +
                ", rider=" + rider +
                ", driver=" + driver +
                ", driverRating=" + driverRating +
                ", riderRating=" + riderRating +
                '}';
    }
}