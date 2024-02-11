package org.artem.flight.system.mapper;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.ShoppingCart;
import org.artem.flight.system.dto.ReservationSeatReadDto;
import org.artem.flight.system.dto.ShoppingCartItemReadDto;
import org.artem.flight.system.dto.ShoppingCartReadDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ShoppingCartReadMapper implements Mapper<ShoppingCart, ShoppingCartReadDto> {

    private final ShoppingCartItemReadMapper shoppingCartItemReadMapper;

    @Override
    public ShoppingCartReadDto map(ShoppingCart object) {
        List<ShoppingCartItemReadDto> items = getItems(object);

        return new ShoppingCartReadDto(object.getId(),
                object.getSessionId(),
                getTotalSum(items),
                items);
    }

    private List<ShoppingCartItemReadDto> getItems(ShoppingCart object) {
        return Optional.ofNullable(object.getItems())
                .orElseGet(Collections::emptyList).stream()
                .map(shoppingCartItemReadMapper::map)
                .toList();
    }

    private int getTotalSum(List<ShoppingCartItemReadDto> items) {
        return items.stream()
                .map(ShoppingCartItemReadDto::getReservationSeat)
                .map(ReservationSeatReadDto::getPrice)
                .mapToInt(price -> price)
                .sum();
    }
}