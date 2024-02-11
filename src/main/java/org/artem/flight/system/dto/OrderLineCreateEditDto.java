package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class OrderLineCreateEditDto {

    Long customerOrderId;
    Long reservationSeatId;

    Integer price;
}