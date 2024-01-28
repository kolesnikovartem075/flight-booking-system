package org.artem.flight.system.mapper;

import org.artem.flight.system.database.entity.ShoppingCartItem;
import org.artem.flight.system.dto.ReservationSeatReadDto;
import org.artem.flight.system.dto.ShoppingCartItemReadDto;
import org.artem.flight.system.mapper.reservation.ReservationSeatReadMapper;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartItemReadMapper implements Mapper<ShoppingCartItem, ShoppingCartItemReadDto> {


    ReservationSeatReadMapper reservationSeatReadMapper;

    @Override
    public ShoppingCartItemReadDto map(ShoppingCartItem object) {
        var reservationSeat = getReservationSeatReadDto(object);

        return new ShoppingCartItemReadDto(object.getId(), reservationSeat);
    }

    private ReservationSeatReadDto getReservationSeatReadDto(ShoppingCartItem object) {
        return reservationSeatReadMapper.map(object.getReservationSeat());
    }
}