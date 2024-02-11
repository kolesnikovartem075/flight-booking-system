package org.artem.flight.system.dto;

import lombok.Value;

import java.util.List;

@Value
public class ShoppingCartReadDto {

    Long id;

    String sessionId;

    Integer totalPrice;

    List<ShoppingCartItemReadDto> items;
}