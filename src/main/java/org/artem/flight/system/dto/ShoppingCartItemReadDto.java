package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class ShoppingCartItemReadDto {

    Long id;

    ReservationSeatReadDto reservationSeat;
}