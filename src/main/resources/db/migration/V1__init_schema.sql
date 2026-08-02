-- ===============================
-- EXTENSIONS
-- ===============================
CREATE EXTENSION IF NOT EXISTS postgis;

-- ===============================
-- USERS
-- ===============================
CREATE TABLE app_user (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)
);

CREATE INDEX idx_user_email ON app_user(email);

-- ===============================
-- USER ROLES (ElementCollection)
-- ===============================
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id, role),
    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE
);

-- ===============================
-- DRIVER
-- ===============================
CREATE TABLE driver (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE,
    rating DOUBLE PRECISION,
    available BOOLEAN,
    vehicle_id VARCHAR(255),
    current_location GEOMETRY(Point, 4326),
    CONSTRAINT fk_driver_user
        FOREIGN KEY (user_id) REFERENCES app_user(id)
);

CREATE INDEX idx_driver_vehicle_id ON driver(vehicle_id);

-- ===============================
-- RIDER
-- ===============================
CREATE TABLE rider (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE,
    rating DOUBLE PRECISION,
    CONSTRAINT fk_rider_user
        FOREIGN KEY (user_id) REFERENCES app_user(id)
);

-- ===============================
-- RIDE
-- ===============================
CREATE TABLE ride (
    id BIGSERIAL PRIMARY KEY,
    pickup_location GEOMETRY(Point, 4326),
    drop_off_location GEOMETRY(Point, 4326),
    created_time TIMESTAMP DEFAULT now(),
    rider_id BIGINT,
    driver_id BIGINT,
    payment_method VARCHAR(30),
    ride_status VARCHAR(30),
    otp VARCHAR(20),
    fare DOUBLE PRECISION,
    started_at TIMESTAMP,
    ended_at TIMESTAMP,
    CONSTRAINT fk_ride_rider FOREIGN KEY (rider_id) REFERENCES rider(id),
    CONSTRAINT fk_ride_driver FOREIGN KEY (driver_id) REFERENCES driver(id)
);

CREATE INDEX idx_ride_rider ON ride(rider_id);
CREATE INDEX idx_ride_driver ON ride(driver_id);

-- ===============================
-- RIDE REQUEST
-- ===============================
CREATE TABLE ride_request (
    id BIGSERIAL PRIMARY KEY,
    pickup_location GEOMETRY(Point, 4326),
    drop_off_location GEOMETRY(Point, 4326),
    requested_time TIMESTAMP DEFAULT now(),
    rider_id BIGINT,
    payment_method VARCHAR(30),
    ride_request_status VARCHAR(30),
    fare DOUBLE PRECISION,
    CONSTRAINT fk_ride_request_rider
        FOREIGN KEY (rider_id) REFERENCES rider(id)
);

CREATE INDEX idx_ride_request_rider ON ride_request(rider_id);

-- ===============================
-- PAYMENT
-- ===============================
CREATE TABLE payment (
    id BIGSERIAL PRIMARY KEY,
    payment_method VARCHAR(30),
    ride_id BIGINT UNIQUE,
    amount DOUBLE PRECISION,
    payment_status VARCHAR(30),
    payment_time TIMESTAMP DEFAULT now(),
    CONSTRAINT fk_payment_ride
        FOREIGN KEY (ride_id) REFERENCES ride(id)
);

-- ===============================
-- WALLET
-- ===============================
CREATE TABLE wallet (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE NOT NULL,
    balance DOUBLE PRECISION DEFAULT 0,
    CONSTRAINT fk_wallet_user
        FOREIGN KEY (user_id) REFERENCES app_user(id)
);

-- ===============================
-- WALLET TRANSACTION
-- ===============================
CREATE TABLE wallet_transaction (
    id BIGSERIAL PRIMARY KEY,
    amount DOUBLE PRECISION,
    transaction_type VARCHAR(30),
    transaction_method VARCHAR(30),
    ride_id BIGINT,
    transaction_id VARCHAR(255),
    wallet_id BIGINT,
    time_stamp TIMESTAMP DEFAULT now(),
    CONSTRAINT fk_wallet_tx_wallet
        FOREIGN KEY (wallet_id) REFERENCES wallet(id),
    CONSTRAINT fk_wallet_tx_ride
        FOREIGN KEY (ride_id) REFERENCES ride(id)
);

CREATE INDEX idx_wallet_transaction_wallet ON wallet_transaction(wallet_id);
CREATE INDEX idx_wallet_transaction_ride ON wallet_transaction(ride_id);

-- ===============================
-- RATING
-- ===============================
CREATE TABLE rating (
    id BIGSERIAL PRIMARY KEY,
    ride_id BIGINT UNIQUE,
    rider_id BIGINT,
    driver_id BIGINT,
    driver_rating INTEGER,
    rider_rating INTEGER,
    CONSTRAINT fk_rating_ride FOREIGN KEY (ride_id) REFERENCES ride(id),
    CONSTRAINT fk_rating_rider FOREIGN KEY (rider_id) REFERENCES rider(id),
    CONSTRAINT fk_rating_driver FOREIGN KEY (driver_id) REFERENCES driver(id)
);

CREATE INDEX idx_rating_rider ON rating(rider_id);
CREATE INDEX idx_rating_driver ON rating(driver_id);
