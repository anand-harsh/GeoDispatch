# GeoDispatch

GeoDispatch is a Spring Boot backend for ride booking and dispatch. It provides APIs for riders and drivers to manage authentication, ride requests, driver matching, fare calculation, payments, wallets, ratings, and ride history.

The application uses PostgreSQL with PostGIS for location-based driver queries, JWT for authentication, Spring Security for authorization, and strategy-based components for driver matching, fare calculation, and payment processing.

## Features

- User registration and login
- JWT access and refresh tokens
- Rider and driver roles
- Driver onboarding
- Ride request and ride lifecycle management
- Nearby driver matching using PostGIS
- Rating-based driver matching
- Distance-based fare calculation
- Time-based surge pricing
- Cash and wallet payments
- Rider and driver wallets
- Rider and driver ratings
- OTP-based ride start verification
- Paginated ride history
- Request validation
- Centralized exception handling
- Consistent API responses
- Swagger/OpenAPI documentation
- Spring Boot Actuator endpoints

## Tech Stack

### Backend

- Java 21
- Spring Boot 3.4.1
- Spring Web
- Spring Data JPA
- Spring Security
- Hibernate
- Hibernate Spatial
- Jakarta Validation

### Database

- PostgreSQL
- PostGIS
- JTS Geometry

### Security

- Spring Security
- JWT
- BCrypt

### API Documentation

- Swagger / OpenAPI
- Spring Boot Actuator

### Build and Testing

- Maven
- JUnit 5
- Spring Boot Test

### Libraries

- Lombok
- ModelMapper
- JavaMail

## Architecture

The application follows a layered architecture:

```text
Client
  |
  v
Controller
  |
  v
Service
  |
  +----> Strategy
  |
  v
Repository
  |
  v
PostgreSQL + PostGIS
