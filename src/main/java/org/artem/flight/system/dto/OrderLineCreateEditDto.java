package org.artem.flight.system.dto;

import lombok.Data;
import lombok.Value;

@Data
public class OrderLineCreateEditDto {

    Long customerOrderId;
    Long reservationSeatId;

    String firstName;
    String lastName;

    Integer price;
}