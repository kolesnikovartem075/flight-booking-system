--liquibase formatted sql

--changeset artem:1
create table airline
(
    id    BIGSERIAL PRIMARY KEY,
    title varchar(128)

);

--changeset artem:2
create table address
(
    id   BIGSERIAL PRIMARY KEY,
    name varchar(128)

);


--changeset artem:3
create table airport
(
    id         BIGSERIAL PRIMARY KEY,
    address_id bigint references address,
    name       varchar(128)

);

--changeset artem:4
create table flight
(
    id            BIGSERIAL PRIMARY KEY,
    flight_no     varchar(128),
    airport_id    bigint references airport,
    airline_id    bigint references airline,
    seat_capacity int
);

--changeset artem:5
create table seat
(
    id        BIGSERIAL PRIMARY KEY,
    flight_id bigint references flight,
    number_no varchar(64),
    rank      varchar(64)
);

--changeset artem:6
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

--changeset artem:7
create table schedule_seat
(
    id          BIGSERIAL PRIMARY KEY,
    schedule_id bigint references schedule,
    seat_id     bigint references seat,
    status      varchar(64),
    price       int,
    unique (schedule_id, seat_id)
);


