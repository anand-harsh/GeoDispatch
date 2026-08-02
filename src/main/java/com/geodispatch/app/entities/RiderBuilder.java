package com.geodispatch.app.entities;

public class RiderBuilder {

    private Long id;
    private User user;
    private Double rating;

    public RiderBuilder() {
    }

    public RiderBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public RiderBuilder user(User user) {
        this.user = user;
        return this;
    }

    public RiderBuilder rating(Double rating) {
        this.rating = rating;
        return this;
    }

    public Rider build() {
        return new Rider(
                id,
                user,
                rating
        );
    }

    @Override
    public String toString() {
        return "RiderBuilder{" +
                "id=" + id +
                ", user=" + user +
                ", rating=" + rating +
                '}';
    }
}