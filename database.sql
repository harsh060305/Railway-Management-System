-- Railway Management System Database
CREATE DATABASE IF NOT EXISTS railway_db;
USE railway_db;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE admins (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE stations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    city VARCHAR(100)
);

CREATE TABLE trains (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    train_no VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) -- e.g. Express, Local, Superfast
);

CREATE TABLE routes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    train_id BIGINT,
    source_station_id BIGINT,
    dest_station_id BIGINT,
    distance_km INT,
    FOREIGN KEY (train_id) REFERENCES trains(id) ON DELETE CASCADE,
    FOREIGN KEY (source_station_id) REFERENCES stations(id) ON DELETE CASCADE,
    FOREIGN KEY (dest_station_id) REFERENCES stations(id) ON DELETE CASCADE
);

CREATE TABLE schedules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    train_id BIGINT,
    station_id BIGINT,
    arrival_time TIME,
    departure_time TIME,
    day_offset INT DEFAULT 0, -- 0 for same day, 1 for next day etc.
    FOREIGN KEY (train_id) REFERENCES trains(id) ON DELETE CASCADE,
    FOREIGN KEY (station_id) REFERENCES stations(id) ON DELETE CASCADE
);

CREATE TABLE fare (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    train_id BIGINT,
    train_class VARCHAR(10), -- e.g. 1A, 2A, 3A, SL, CC
    base_fare_per_km DECIMAL(10,2),
    FOREIGN KEY (train_id) REFERENCES trains(id) ON DELETE CASCADE
);

CREATE TABLE availability (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    train_id BIGINT,
    travel_date DATE,
    train_class VARCHAR(10),
    total_seats INT,
    available_seats INT,
    FOREIGN KEY (train_id) REFERENCES trains(id) ON DELETE CASCADE
);

CREATE TABLE bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    train_id BIGINT,
    pnr VARCHAR(20) UNIQUE NOT NULL,
    journey_date DATE,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20), -- CONFIRMED, CANCELLED, WAITLIST
    total_amount DECIMAL(10,2),
    source_station_id BIGINT,
    dest_station_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (train_id) REFERENCES trains(id) ON DELETE CASCADE,
    FOREIGN KEY (source_station_id) REFERENCES stations(id),
    FOREIGN KEY (dest_station_id) REFERENCES stations(id)
);

CREATE TABLE tickets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_id BIGINT,
    passenger_name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    seat_no VARCHAR(10),
    train_class VARCHAR(10),
    FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE
);

CREATE TABLE payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_id BIGINT,
    amount DECIMAL(10,2),
    payment_method VARCHAR(50),
    transaction_id VARCHAR(100) UNIQUE,
    status VARCHAR(20), -- SUCCESS, FAILED
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE
);

-- Insert dummy data
INSERT INTO admins (username, password, email) VALUES ('admin', 'admin123', 'admin@railway.com');
INSERT INTO users (full_name, email, password, phone) VALUES ('John Doe', 'john@example.com', 'user123', '9876543210');

INSERT INTO stations (code, name, city) VALUES 
('NDLS', 'New Delhi', 'Delhi'),
('CSMT', 'Chhatrapati Shivaji Maharaj Terminus', 'Mumbai'),
('HWH', 'Howrah Junction', 'Kolkata'),
('MAS', 'Chennai Central', 'Chennai');

INSERT INTO trains (train_no, name, type) VALUES 
('12952', 'Mumbai Rajdhani', 'Rajdhani Express'),
('12301', 'Howrah Rajdhani', 'Rajdhani Express'),
('12616', 'Grand Trunk Express', 'Express');

INSERT INTO routes (train_id, source_station_id, dest_station_id, distance_km) VALUES 
(1, 1, 2, 1384),
(2, 3, 1, 1447),
(3, 1, 4, 2182);

INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, day_offset) VALUES 
(1, 1, '16:30:00', '16:55:00', 0),
(1, 2, '08:35:00', '08:35:00', 1);

INSERT INTO fare (train_id, train_class, base_fare_per_km) VALUES 
(1, '3A', 2.50),
(1, '2A', 3.50),
(1, '1A', 5.00);

INSERT INTO availability (train_id, travel_date, train_class, total_seats, available_seats) VALUES 
(1, CURRENT_DATE + INTERVAL 1 DAY, '3A', 100, 50),
(1, CURRENT_DATE + INTERVAL 1 DAY, '2A', 50, 20),
(1, CURRENT_DATE + INTERVAL 1 DAY, '1A', 20, 5);
