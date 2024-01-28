create table flight
(
    id            bigserial,
    flight_no     varchar(128),
    airline_id    int references airline,
    seat_capacity int
);

create table airline
(
    id    serial,
    title varchar(128)

);

create table seat
(
    id        bigserial,
    flight_id bigint references flight,
    number_no varchar(64),
    rank      varchar(64)
);

create table schedule
(
    id               bigserial,
    flight_id        bigint references flight,
    start_airport_id int references airport,
    end_airport_id   int references airport,
    start_time       timestamp,
    end_time         timestamp,
    status           varchar(64)

);


create table reservation_seat
(
    id          bigserial,
    schedule_id bigint references schedule,
    seat_id     bigint references seat,
    status      varchar(64),
    unique (schedule_id, seat_id)
);

create table airport
(
    id         int,
    flight_id  bigint references flight,
    address_id bigint references address,
    name       varchar(128)

);

create table address
(
    id   bigserial,
    name varchar(128)

);

CREATE TABLE shopping_cart
(
    id          BIGSERIAL PRIMARY KEY,
    customer_id BIGINT REFERENCES customer,
    UNIQUE (id, customer_id)
);

CREATE TABLE shopping_cart_item
(
    id                 BIGSERIAL PRIMARY KEY,
    shopping_cart_id   BIGINT REFERENCES shopping_cart ON DELETE CASCADE,
    schedule_seat_id BIGINT REFERENCES schedule_seat ON DELETE CASCADE
);