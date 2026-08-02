package com.geodispatch.app.dto;

import java.util.Arrays;
import java.util.Objects;

public class PointDto {

    private double[] coordinates;
    private String type = "Point";

    public PointDto() {
    }

    public PointDto(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PointDto)) return false;

        PointDto other = (PointDto) obj;

        return Arrays.equals(coordinates, other.coordinates)
                && Objects.equals(type, other.type);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(coordinates);
        result = 31 * result + Objects.hashCode(type);
        return result;
    }

    @Override
    public String toString() {
        return "PointDto{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", type='" + type + '\'' +
                '}';
    }
}