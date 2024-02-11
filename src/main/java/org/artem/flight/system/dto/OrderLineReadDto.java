package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class OrderLineReadDto {

    Long id;
    ReservationSeatReadDto reservationSeatReadDto;

    Integer quantity;
    Integer price;
}