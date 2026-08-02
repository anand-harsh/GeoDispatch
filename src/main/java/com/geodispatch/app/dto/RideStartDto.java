package com.geodispatch.app.dto;

import java.util.Objects;

public class RideStartDto {

    private String otp;

    public RideStartDto() {
    }

    public RideStartDto(String otp) {
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RideStartDto)) {
            return false;
        }

        RideStartDto other = (RideStartDto) obj;
        return Objects.equals(otp, other.otp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(otp);
    }

    @Override
    public String toString() {
        return "RideStartDto{" +
                "otp='" + otp + '\'' +
                '}';
    }
}