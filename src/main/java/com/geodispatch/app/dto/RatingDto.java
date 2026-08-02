package com.geodispatch.app.dto;

import java.util.Objects;

public class RatingDto {

    private Long rideId;
    private Integer rating;

    public RatingDto() {
    }

    public RatingDto(Long rideId, Integer rating) {
        this.rideId = rideId;
        this.rating = rating;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RatingDto)) {
            return false;
        }

        RatingDto other = (RatingDto) obj;

        return Objects.equals(rideId, other.rideId)
                && Objects.equals(rating, other.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rideId, rating);
    }

    @Override
    public String toString() {
        return "RatingDto{" +
                "rideId=" + rideId +
                ", rating=" + rating +
                '}';
    }
}