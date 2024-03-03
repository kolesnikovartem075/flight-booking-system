--liquibase formatted sql

--changeset artem:1
CREATE TABLE airline
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(128)

);

--changeset artem:2
CREATE TABLE country
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) UNIQUE

);

--changeset artem:3
CREATE TABLE city
(
    id         BIGSERIAL PRIMARY KEY,
    country_id BIGINT REFERENCES country,
    name       VARCHAR(128)

);


--changeset artem:4
CREATE TABLE airport
(
    id      BIGSERIAL PRIMARY KEY,
    city_id bigint REFERENCES city,
    name    VARCHAR(128)

);

--changeset artem:5
CREATE TABLE flight
(
    id            BIGSERIAL PRIMARY KEY,
    flight_no     VARCHAR(128) UNIQUE,
    airline_id    bigint REFERENCES airline,
    seat_capacity int
);

--changeset artem:6
CREATE TABLE seat
(
    id        BIGSERIAL PRIMARY KEY,
    flight_id bigint REFERENCES flight,
    number_no VARCHAR(64) UNIQUE,
    rank      VARCHAR(64)
);

--changeset artem:7
CREATE TABLE schedule
(
    id               BIGSERIAL PRIMARY KEY,
    flight_id        bigint REFERENCES flight,
    start_airport_id bigint REFERENCES airport,
    end_airport_id   bigint REFERENCES airport,
    start_time       TIMESTAMP,
    end_time         TIMESTAMP,
    status           VARCHAR(64)

);

--changeset artem:8
CREATE TABLE customer
(
    id    BIGSERIAL PRIMARY KEY,
    email VARCHAR(128),
    UNIQUE (email)
);

--changeset artem:9
CREATE TABLE reservation_seat
(
    id          BIGSERIAL PRIMARY KEY,
    schedule_id bigint REFERENCES schedule,
    seat_id     bigint REFERENCES seat,
    status      VARCHAR(64),
    price       int,
    UNIQUE (schedule_id, seat_id)
);

--changeset artem:10
CREATE TABLE shopping_cart
(
    id           BIGSERIAL PRIMARY KEY,
    session_id   VARCHAR(32),
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (id, session_id)
);

--changeset artem:11
CREATE TABLE shopping_cart_item
(
    id                  BIGSERIAL PRIMARY KEY,
    shopping_cart_id    BIGINT REFERENCES shopping_cart ON DELETE CASCADE,
    reservation_seat_id BIGINT REFERENCES reservation_seat ON DELETE CASCADE
);

--changeset artem:12
CREATE TABLE customer_order
(
    id           BIGSERIAL PRIMARY KEY,
    customer_id  BIGINT REFERENCES customer,
    order_status VARCHAR(16),
    order_total  INT,
    date_created DATE
);

--changeset artem:13
CREATE TABLE customer_order_line
(
    id                  BIGSERIAL PRIMARY KEY,
    reservation_seat_id BIGINT REFERENCES reservation_seat ON DELETE CASCADE,
    customer_order_id   BIGINT REFERENCES customer_order ON DELETE CASCADE,
    price               INT
);