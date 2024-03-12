package org.artem.flight.system.mapper;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.ReservationSeat;
import org.artem.flight.system.database.entity.ReservationStatus;
import org.artem.flight.system.database.entity.ShoppingCart;
import org.artem.flight.system.database.entity.ShoppingCartItem;
import org.artem.flight.system.database.repository.ReservationSeatRepository;
import org.artem.flight.system.database.repository.ShoppingCartRepository;
import org.artem.flight.system.dto.ShoppingCartItemCreateEditDto;
import org.artem.flight.system.service.ReservationSeatService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShoppingCartItemCreateEditMapper implements Mapper<ShoppingCartItemCreateEditDto, ShoppingCartItem> {

    private final ReservationSeatRepository reservationSeatRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCartItem map(ShoppingCartItemCreateEditDto object) {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        ShoppingCart shoppingCart = getShoppingCart(object);
        ReservationSeat reservationSeat = getReservationSeat(object);
        reservationSeat.setStatus(ReservationStatus.PENDING);
        reservationSeatRepository.saveAndFlush(reservationSeat);

        shoppingCartItem.setShoppingCart(shoppingCart);
        shoppingCartItem.setReservationSeat(reservationSeat);

        return shoppingCartItem;
    }

    private ReservationSeat getReservationSeat(ShoppingCartItemCreateEditDto shoppingCartItemDto) {
        return reservationSeatRepository.findById(shoppingCartItemDto.getReservationSeatId())
                .orElseThrow();
    }

    private ShoppingCart getShoppingCart(ShoppingCartItemCreateEditDto shoppingCartItemDto) {
        return shoppingCartRepository.findById(shoppingCartItemDto.getShoppingCartId())
                .orElseThrow();
    }
}
