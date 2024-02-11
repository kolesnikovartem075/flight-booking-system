package org.artem.flight.system.mapper;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.Order;
import org.artem.flight.system.database.entity.OrderLine;
import org.artem.flight.system.database.entity.ReservationSeat;
import org.artem.flight.system.database.repository.OrderRepository;
import org.artem.flight.system.database.repository.ReservationSeatRepository;
import org.artem.flight.system.dto.OrderLineCreateEditDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderLineCreateEditMapper implements Mapper<OrderLineCreateEditDto, OrderLine> {

    private final ReservationSeatRepository reservationSeatRepository;
    private final OrderRepository orderRepository;

    @Override
    public OrderLine map(OrderLineCreateEditDto object) {
        OrderLine order = new OrderLine();
        copy(object, order);

        return order;
    }

    @Override
    public OrderLine map(OrderLineCreateEditDto fromObject, OrderLine toObject) {
        copy(fromObject, toObject);
        return toObject;
    }


    private void copy(OrderLineCreateEditDto orderLine, OrderLine entity) {
        var reservationSeat = getReservationSeat(orderLine);
        var order = getOrder(orderLine);

        entity.setReservationSeat(reservationSeat);
        entity.setOrder(order);
        entity.setPrice(orderLine.getPrice());
    }

    private Order getOrder(OrderLineCreateEditDto orderLine) {
        return orderRepository.findById(orderLine.getCustomerOrderId())
                .orElseThrow();
    }

    private ReservationSeat getReservationSeat(OrderLineCreateEditDto orderLine) {
        return reservationSeatRepository.findById(orderLine.getReservationSeatId())
                .orElseThrow();
    }
}