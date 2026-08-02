package com.geodispatch.app.dto;

import java.util.Objects;

public class RiderDto {

    private Long id;
    private UserDto user;
    private Double rating;

    public RiderDto() {
    }

    public RiderDto(Long id, UserDto user, Double rating) {
        this.id = id;
        this.user = user;
        this.rating = rating;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RiderDto)) {
            return false;
        }

        RiderDto other = (RiderDto) obj;

        return Objects.equals(id, other.id)
                && Objects.equals(user, other.user)
                && Objects.equals(rating, other.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, rating);
    }

    @Override
    public String toString() {
        return "RiderDto{" +
                "id=" + id +
                ", user=" + user +
                ", rating=" + rating +
                '}';
    }
}