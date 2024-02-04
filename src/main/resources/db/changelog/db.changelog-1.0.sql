--liquibase formatted sql

--changeset artem:1
create table airline
(
    id    BIGSERIAL PRIMARY KEY,
    title varchar(128)

);

--changeset artem:2
create table country
(
    id   BIGSERIAL PRIMARY KEY,
    name varchar(128) unique

);

--changeset artem:3
create table city
(
    id         BIGSERIAL PRIMARY KEY,
    country_id BIGINT REFERENCES country,
    name       varchar(128)

);


--changeset artem:4
create table airport
(
    id      BIGSERIAL PRIMARY KEY,
    city_id bigint references city,
    name    varchar(128)

);

--changeset artem:5
create table flight
(
    id            BIGSERIAL PRIMARY KEY,
    flight_no     varchar(128) UNIQUE,
    airport_id    bigint references airport,
    airline_id    bigint references airline,
    seat_capacity int
);

--changeset artem:6
create table seat
(
    id        BIGSERIAL PRIMARY KEY,
    flight_id bigint references flight,
    number_no varchar(64)  UNIQUE,
    rank      varchar(64)
);

--changeset artem:7
create table schedule
(
    id               BIGSERIAL PRIMARY KEY,
    flight_id        bigint references flight,
    start_airport_id bigint references airport,
    end_airport_id   bigint references airport,
    start_time       timestamp,
    end_time         timestamp,
    status           varchar(64)

);

--changeset artem:8
create table customer
(
    id    BIGSERIAL PRIMARY KEY,
    email varchar(128),
    unique (email)
);

--changeset artem:9
create table reservation_seat
(
    id          BIGSERIAL PRIMARY KEY,
    schedule_id bigint references schedule,
    seat_id     bigint references seat,
    customer_id bigint references customer,
    status      varchar(64),
    price       int,
    unique (schedule_id, seat_id)
);

--changeset artem:10
CREATE TABLE shopping_cart
(
    id          BIGSERIAL PRIMARY KEY,
    customer_id BIGINT REFERENCES customer,
    UNIQUE (id, customer_id)
);

--changeset artem:11
CREATE TABLE shopping_cart_item
(
    id                  BIGSERIAL PRIMARY KEY,
    shopping_cart_id    BIGINT REFERENCES reservation_seat ON DELETE CASCADE,
    reservation_seat_id BIGINT REFERENCES reservation_seat ON DELETE CASCADE
);