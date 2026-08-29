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

```
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
```

The main layers are:

- **controllers/** — REST endpoints
- **services/** — Business logic
- **strategies/** — Driver matching, fare calculation, payment processing
- **repositories/** — Database access
- **entities/** — Database entities
- **dto/** — Request and response objects
- **security/** — JWT authentication
- **advices/** — Exception and response handling
- **configs/** — Application configuration

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/geodispatch/app/
│   │       ├── advices/
│   │       ├── configs/
│   │       ├── controllers/
│   │       ├── dto/
│   │       ├── entities/
│   │       ├── exceptions/
│   │       ├── repositories/
│   │       ├── security/
│   │       ├── services/
│   │       ├── strategies/
│   │       └── utils/
│   │
│   └── resources/
│
└── test/
    └── java/
        └── com/geodispatch/app/
```

## Authentication

Authentication is implemented using Spring Security and JWT.

The login flow is:

```
POST /auth/login
       |
       v
AuthenticationManager
       |
       v
Validate credentials
       |
       v
Generate JWT
       |
       +----> Access Token
       |
       +----> Refresh Token
```

Authenticated requests use:

```
Authorization: Bearer <access-token>
```

The JWT authentication filter reads the token from the Authorization header, extracts the user ID, loads the user, and sets the authenticated user in Spring Security's context.

Access tokens are short-lived, while refresh tokens are used to generate new access tokens.

## Roles

The application uses role-based authorization.

Supported roles include:

- `ROLE_RIDER`
- `ROLE_DRIVER`
- `ROLE_ADMIN`

### Rider

Riders can:

- Request rides
- Cancel confirmed rides
- Rate drivers
- View their profile
- View their ride history

### Driver

Drivers can:

- Accept ride requests
- Start rides
- End rides
- Cancel rides
- Rate riders
- View their profile
- View their ride history

### Admin

Admins can onboard users as drivers.

## Ride Lifecycle

A ride follows a defined state flow:

```
PENDING
   |
   | Driver accepts
   v
CONFIRMED
   |
   | Driver starts with OTP
   v
ONGOING
   |
   | Driver ends ride
   v
ENDED
```

A confirmed ride can also be cancelled:

```
CONFIRMED
    |
    | Cancel
    v
CANCELLED
```

## Driver Matching

Driver matching is implemented through the `DriverMatchingStrategy` interface.

The application currently provides two strategies.

### Nearest Driver

The nearest available drivers are selected based on their location relative to the pickup point.

The repository uses PostGIS spatial functions such as:

- `ST_DWithin(...)`
- `ST_Distance(...)`

### Highest Rated Driver

For riders with a rating of 4.8 or higher, the application uses the highest-rated nearby drivers.

The strategy selection is:

```
Rider rating >= 4.8
        |
        v
Highest Rated Driver Strategy

Rider rating < 4.8
        |
        v
Nearest Driver Strategy
```

## Fare Calculation

Fare calculation is implemented through the `RideFareCalculationStrategy` interface.

The default calculation is based on distance:

```
Fare = Distance × Fare Multiplier
```

The current base fare multiplier is 10.0.

### Surge Pricing

A separate strategy is used for surge pricing.

The current surge window is:

```
18:00 - 21:00
```

During this period:

```
Surge Fare = Distance × 10 × 2
```

The current surge factor is 2.0.

Outside the configured surge window, the default fare calculation strategy is used.

## Payment

Payment processing is implemented through the `PaymentStrategy` interface.

Supported payment methods:

- `CASH`
- `WALLET`

The payment strategy is selected by `PaymentStrategyManager`.

```
Payment Method
      |
      +---- CASH ------> CashPaymentStrategy
      |
      +---- WALLET ----> WalletPaymentStrategy
```

The current platform commission is 30%.

## Wallet

Users can have an associated wallet.

Wallet operations include:

- Add money
- Deduct money
- Find wallet by user
- Withdraw balance
- Create wallet transactions

Wallet transactions are stored separately and can be associated with rides.

## Ratings

Riders and drivers can rate each other after a ride has been completed.

A rating is associated with:

- Ride
- Rider
- Driver
- Rider rating
- Driver rating

The application checks that the ride is completed before allowing a rating.

## OTP Ride Verification

Drivers must provide the ride OTP before starting a ride.

The flow is:

```
Start Ride Request
       |
       v
Validate Driver
       |
       v
Validate Ride
       |
       v
Validate OTP
       |
       v
ONGOING
```

## Location and PostGIS

The application uses PostgreSQL with PostGIS for geographic queries.

Location data is represented using JTS geometry and Hibernate Spatial.

Driver discovery uses spatial operations including:

- `ST_DWithin(...)`
- `ST_Distance(...)`

This allows the database to filter and order drivers based on their distance from a pickup location.

## Pagination

Ride history endpoints support pagination.

Example:

```
GET /riders/getMyRides?pageOffset=0&pageSize=10
```

Driver ride history:

```
GET /drivers/getMyRides?pageOffset=0&pageSize=10
```

Results are ordered by creation time and ID in descending order.

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/signup` | Register a rider |
| POST | `/auth/login` | Login |
| POST | `/auth/refresh` | Generate a new access token |
| POST | `/auth/onBoardNewDriver/{userId}` | Onboard a driver |

### Rider

| Method | Endpoint | Description |
|---|---|---|
| POST | `/riders/requestRide` | Request a ride |
| POST | `/riders/cancelRide/{rideId}` | Cancel a confirmed ride |
| POST | `/riders/rateDriver` | Rate a driver |
| GET | `/riders/getMyProfile` | Get rider profile |
| GET | `/riders/getMyRides` | Get rider ride history |

### Driver

| Method | Endpoint | Description |
|---|---|---|
| POST | `/drivers/acceptRide/{rideRequestId}` | Accept a ride request |
| POST | `/drivers/startRide/{rideRequestId}` | Start a ride using OTP |
| POST | `/drivers/endRide/{rideId}` | End a ride |
| POST | `/drivers/cancelRide/{rideId}` | Cancel a ride |
| POST | `/drivers/rateRider` | Rate a rider |
| GET | `/drivers/getMyProfile` | Get driver profile |
| GET | `/drivers/getMyRides` | Get driver ride history |

## API Response Handling

The application provides a common response structure for API responses.

Errors are centrally handled for cases including:

- Resource not found
- Conflicting requests
- Authentication failures
- Invalid JWTs
- Access denied
- Validation failures
- Unexpected exceptions

Validation errors return the failed fields as part of the error response.

## Swagger

Swagger/OpenAPI is included for API documentation.

After starting the application:

```
http://localhost:4556/swagger-ui/index.html
```

OpenAPI specification:

```
http://localhost:4556/v3/api-docs
```

## Health Check

Spring Boot Actuator is enabled for application monitoring.

The application also provides:

```
GET /ping
```

## Getting Started

### Prerequisites

Install:

- Java 21
- PostgreSQL
- PostGIS
- Maven (optional)

Verify Java:

```bash
java -version
```

### 1. Clone the Repository

```bash
git clone <repository-url>
cd <repository-directory>
```

### 2. Create the Database

Create a PostgreSQL database:

```sql
CREATE DATABASE geodispatch;
```

Enable PostGIS:

```sql
CREATE EXTENSION IF NOT EXISTS postgis;
```

### 3. Configure the Application

Configure your local database and JWT secret.

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/geodispatch
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>

jwt.secretKey=<your-secret-key>
```

Do not commit database passwords, JWT secrets, or other credentials to the repository.

For production, use environment variables or a secrets manager.

### 4. Run the Application

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
.\mvnw.cmd spring-boot:run
```

Or with Maven installed:

```bash
mvn spring-boot:run
```

The application starts on:

```
http://localhost:4556
```

## Running Tests

Linux/macOS:

```bash
./mvnw test
```

Windows:

```bash
.\mvnw.cmd test
```

Or:

```bash
mvn test
```

## Design Patterns

### Strategy Pattern

Strategy-based implementations are used for:

```
Driver Matching
    |
    +-- Nearest Driver
    +-- Highest Rated Driver

Fare Calculation
    |
    +-- Default Fare
    +-- Surge Fare

Payment
    |
    +-- Cash
    +-- Wallet
```

This separates interchangeable business rules and allows additional implementations to be introduced without modifying the existing strategy interface.

### Repository Pattern

Spring Data JPA repositories provide the persistence layer for users, riders, drivers, rides, ride requests, payments, wallets, ratings, and related entities.

### Layered Architecture

Controllers handle HTTP requests, services contain business logic, strategies contain interchangeable business rules, and repositories handle database operations.

## Database Model

The main entities include:

- User
- Rider
- Driver
- RideRequest
- Ride
- Payment
- Wallet
- WalletTransaction
- Rating

Payments and wallet transactions are associated with rides where applicable, while ratings are associated with rides, riders, and drivers.

## Configuration

The project uses environment-specific application configuration.

Sensitive configuration should be supplied through environment-specific configuration and should not be committed to source control.

## Testing

The project includes Spring Boot test infrastructure.

Tests can be executed using Maven:

```bash
./mvnw test
```

## Future Improvements

Possible extensions include:

- WebSocket-based ride status updates
- Real-time driver location updates
- Redis-based driver availability
- Asynchronous driver notifications
- Rate limiting
- Idempotency for critical APIs
- Distributed tracing
- Centralized logging
- Additional integration tests
- End-to-end API tests
- CI/CD automation

## Author

**Harsh Anand**
Software Engineer
