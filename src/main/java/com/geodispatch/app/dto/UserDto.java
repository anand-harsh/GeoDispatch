package com.geodispatch.app.dto;

import com.geodispatch.app.entities.enums.Role;

import java.util.Objects;
import java.util.Set;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private Set<Role> roles;

    public UserDto() {
    }

    public UserDto(Long id, String name, String email, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UserDto)) {
            return false;
        }

        UserDto other = (UserDto) obj;

        return Objects.equals(id, other.id)
                && Objects.equals(name, other.name)
                && Objects.equals(email, other.email)
                && Objects.equals(roles, other.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, roles);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}