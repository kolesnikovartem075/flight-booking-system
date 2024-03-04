-- Insert into airline
INSERT INTO airline (title)
VALUES ('Delta Airlines'),
       ('United Airlines'),
       ('American Airlines');

-- Insert into country
INSERT INTO country (name)
VALUES ('United States'),
       ('Canada'),
       ('United Kingdom'),
       ('Ukraine');

-- Insert into city
INSERT INTO city (country_id, name)
VALUES (1, 'New York'),
       (1, 'Los Angeles'),
       (2, 'Toronto'),
       (3, 'London'),
       (4, 'Kyiv');

-- Insert into airport
INSERT INTO airport (city_id, name)
VALUES (1, 'John F. Kennedy International Airport'),
       (1, 'Los Angeles International Airport'),
       (2, 'Toronto Pearson International Airport'),
       (3, 'Heathrow Airport'),
       (5, 'Boryspil International Airport');

-- Insert into flight
INSERT INTO flight (flight_no, airline_id, seat_capacity)
VALUES ('DL123', 1, 200),
       ('UA456', 2, 180),
       ('AA789', 3, 220);

-- Insert into schedule
INSERT INTO schedule (flight_id, start_airport_id, end_airport_id, start_time, end_time, status)
VALUES (1, 1, 2, '2024-02-10 08:00:00', '2024-02-10 12:00:00', 'ON_TIME'),
       (2, 2, 3, '2024-02-15 10:00:00', '2024-02-15 14:00:00', 'DELAYED'),
       (3, 3, 1, '2024-02-20 12:00:00', '2024-02-20 16:00:00', 'ON_TIME');

-- Insert into customer
INSERT INTO customer (email)
VALUES ('john.doe@example.com'),
       ('jane.smith@example.com'),
       ('bob.jones@example.com');

-- Insert into seat
INSERT INTO seat (flight_id, number_no, rank)
VALUES (1, 'A001', 'ECONOMY'),
       (1, 'B001', 'FIRST_CLASS'),
       (2, 'C001', 'FIRST_CLASS'),
       (3, 'D001', 'FIRST_CLASS'),
       (1, 'A002', 'FIRST_CLASS'),
       (1, 'B002', 'FIRST_CLASS'),
       (1, 'C002', 'ECONOMY'),
       (2, 'D002', 'ECONOMY'),
       (3, 'E001', 'FIRST_CLASS'),
       (3, 'F001', 'ECONOMY');

INSERT INTO reservation_seat (schedule_id, seat_id, status, price)
VALUES (1, 2, 'RESERVED', 550),
       (1, 3, 'FREE', 500),
       (2, 4, 'RESERVED', 350),
       (2, 5, 'FREE', 300),
       (3, 6, 'RESERVED', 600),
       (3, 1, 'FREE', 550);

-- Enums (comments)
-- SeatRank: DELUXE, ECONOMY
-- ReservationStatus: FREE, RESERVED


-- Insert into shopping_cart
INSERT INTO shopping_cart (session_id, date_created)
VALUES ('1', now()),
       ('2', now());

-- Insert into shopping_cart_item
INSERT INTO shopping_cart_item (shopping_cart_id, reservation_seat_id)
VALUES (1, 1),
       (2, 3);