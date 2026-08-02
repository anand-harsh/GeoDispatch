package com.geodispatch.app.dto;

import java.util.Objects;

public class SignupDto {

    private String name;
    private String email;
    private String password;

    public SignupDto() {
    }

    public SignupDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SignupDto)) {
            return false;
        }

        SignupDto other = (SignupDto) obj;

        return Objects.equals(name, other.name)
                && Objects.equals(email, other.email)
                && Objects.equals(password, other.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }

    @Override
    public String toString() {
        return "SignupDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}