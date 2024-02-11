package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class OrderLineReadDto {

    Long id;
    ReservationSeatReadDto reservationSeat;

    Integer price;
}