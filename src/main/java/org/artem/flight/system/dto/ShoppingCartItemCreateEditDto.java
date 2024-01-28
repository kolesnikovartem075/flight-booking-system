package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class ShoppingCartItemCreateEditDto {

    Long shoppingCartId;
    Long reservationSeatId;
}