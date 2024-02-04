-- Insert into airline
INSERT INTO airline (title) VALUES
                                ('Delta Airlines'),
                                ('United Airlines'),
                                ('American Airlines');

-- Insert into country
INSERT INTO country (name) VALUES
                               ('United States'),
                               ('Canada'),
                               ('United Kingdom');

-- Insert into city
INSERT INTO city (country_id, name) VALUES
                                        (1, 'New York'),
                                        (1, 'Los Angeles'),
                                        (2, 'Toronto'),
                                        (3, 'London');

-- Insert into airport
INSERT INTO airport (city_id, name) VALUES
                                        (1, 'John F. Kennedy International Airport'),
                                        (1, 'Los Angeles International Airport'),
                                        (2, 'Toronto Pearson International Airport'),
                                        (3, 'Heathrow Airport');

-- Insert into flight
INSERT INTO flight (flight_no, airport_id, airline_id, seat_capacity) VALUES
                                                                          ('DL123', 1, 1, 200),
                                                                          ('UA456', 2, 2, 180),
                                                                          ('AA789', 3, 3, 220);

-- Insert into seat
INSERT INTO seat (flight_id, number_no, rank) VALUES
                                                  (1, 'A001', 'First Class'),
                                                  (1, 'B001', 'Business Class'),
                                                  (2, 'C001', 'Economy Class'),
                                                  (3, 'D001', 'Premium Economy');

-- Insert into schedule
INSERT INTO schedule (flight_id, start_airport_id, end_airport_id, start_time, end_time, status) VALUES
                                                                                                     (1, 1, 2, '2024-02-10 08:00:00', '2024-02-10 12:00:00', 'Scheduled'),
                                                                                                     (2, 2, 3, '2024-02-15 10:00:00', '2024-02-15 14:00:00', 'Delayed'),
                                                                                                     (3, 3, 1, '2024-02-20 12:00:00', '2024-02-20 16:00:00', 'On Time');

-- Insert into customer
INSERT INTO customer (email) VALUES
                                 ('john.doe@example.com'),
                                 ('jane.smith@example.com'),
                                 ('bob.jones@example.com');

-- Insert into reservation_seat
INSERT INTO reservation_seat (schedule_id, seat_id, customer_id, status, price) VALUES
                                                                                    (1, 1, 1, 'Confirmed', 500),
                                                                                    (2, 3, 2, 'Pending', 350),
                                                                                    (3, 4, 3, 'Confirmed', 600);

-- Insert into shopping_cart
INSERT INTO shopping_cart (customer_id) VALUES
                                            (1),
                                            (2);

-- Insert into shopping_cart_item
INSERT INTO shopping_cart_item (shopping_cart_id, reservation_seat_id) VALUES
                                                                           (1, 1),
                                                                           (2, 3);


-- Insert more seats into seat
INSERT INTO seat (flight_id, number_no, rank) VALUES
                                                  (1, 'A002', 'DELUXE'),
                                                  (1, 'B002', 'DELUXE'),
                                                  (1, 'C002', 'ECONOMY'),
                                                  (2, 'D002', 'ECONOMY'),
                                                  (3, 'E001', 'DELUXE'),
                                                  (3, 'F001', 'ECONOMY');

-- Insert more reservation_seats
INSERT INTO seat (flight_id, number_no, rank) VALUES
                                                  (1, 'A002', 'DELUXE'),
                                                  (1, 'B002', 'DELUXE'),
                                                  (1, 'C002', 'ECONOMY'),
                                                  (2, 'D002', 'ECONOMY'),
                                                  (3, 'E001', 'DELUXE'),
                                                  (3, 'F001', 'ECONOMY');
-- Enums (comments)
-- SeatRank: DELUXE, ECONOMY
-- ReservationStatus: FREE, RESERVED
-- Insert more reservation_seats with ReservationStatus enum
INSERT INTO reservation_seat (schedule_id, seat_id, customer_id, status, price) VALUES
                                                                                    (1, 2, 1, 'RESERVED', 550),
                                                                                    (1, 3, 1, 'FREE', 500),
                                                                                    (2, 4, 2, 'RESERVED', 350),
                                                                                    (2, 5, 2, 'FREE', 300),
                                                                                    (3, 6, 3, 'RESERVED', 600),
                                                                                    (3, 1, 3, 'FREE', 550);

-- Enums (comments)
-- SeatRank: DELUXE, ECONOMY
-- ReservationStatus: FREE, RESERVED
