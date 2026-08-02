package com.geodispatch.app.dto;

import java.util.Objects;

public class OnboardDriverDto {

    private String vehicleId;

    public OnboardDriverDto() {
    }

    public OnboardDriverDto(String vehicleId) {
        this.vehicleId = vehicleId;
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
        if (!(obj instanceof OnboardDriverDto)) return false;
        OnboardDriverDto other = (OnboardDriverDto) obj;
        return Objects.equals(vehicleId, other.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId);
    }

    @Override
    public String toString() {
        return "OnboardDriverDto{" +
                "vehicleId='" + vehicleId + '\'' +
                '}';
    }
}