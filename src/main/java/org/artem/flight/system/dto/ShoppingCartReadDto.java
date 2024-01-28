package org.artem.flight.system.dto;

import lombok.Value;

import java.util.List;

@Value
public class ShoppingCartReadDto {

    Long id;

    Long customerId;

    Integer totalPrice;

    List<ShoppingCartItemReadDto> items;
}