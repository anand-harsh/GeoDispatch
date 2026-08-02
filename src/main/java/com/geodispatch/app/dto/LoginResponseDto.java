package com.geodispatch.app.dto;

import java.util.Objects;

public class LoginResponseDto {

    private String accessToken;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LoginResponseDto)) return false;
        LoginResponseDto other = (LoginResponseDto) obj;
        return Objects.equals(accessToken, other.accessToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken);
    }

    @Override
    public String toString() {
        return "LoginResponseDto{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}