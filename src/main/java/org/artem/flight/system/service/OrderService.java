package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.OrderRepository;
import org.artem.flight.system.dto.*;
import org.artem.flight.system.mapper.OrderCreateEditMapper;
import org.artem.flight.system.mapper.OrderReadMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {


    private final OrderRepository orderRepository;
    private final OrderReadMapper orderReadMapper = Mappers.getMapper(OrderReadMapper.class);
    private final OrderCreateEditMapper orderCreateEditMapper;
    private final CustomerService customerService;
    private final ShoppingCartService shoppingCartService;
    private final OrderLineService orderLineService;


    public List<OrderReadDto> findAll() {
        return orderRepository.findAll().stream()
                .map(orderReadMapper::map)
                .toList();
    }

    public Optional<OrderReadDto> findById(Long id) {
        return orderRepository.findById(id)
                .map(orderReadMapper::map);
    }

    @Transactional
    public OrderReadDto createOrder(OrderCreateEditDto orderDto) {
        var orderReadDto = create(orderDto);
        var shoppingCart = getShoppingCart(orderDto.getSessionId());

        var orderLines = createOrderLines(orderReadDto, shoppingCart);
        orderLines.forEach(this::updateQuantity);
        deleteShoppingCart(orderDto.getSessionId());

        return orderReadDto;
    }

    @Transactional
    public OrderReadDto create(OrderCreateEditDto orderDto) {
        return Optional.of(orderDto)
                .map(orderCreateEditMapper::map)
                .map(orderRepository::saveAndFlush)
                .map(orderReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public boolean delete(Long id) {
        return orderRepository.findById(id)
                .map(entity -> {
                    orderRepository.delete(entity);
                    orderRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    private void updateQuantity(OrderLineReadDto orderLine) {
//        reservationSeatService.subtractQuantity(orderLine.getReservationSeatReadDto().getId(), orderLine.getQuantity());
    }

    private void deleteShoppingCart(String sessionId) {
        Optional.of(getShoppingCart(sessionId))
                .map(ShoppingCartReadDto::getId)
                .map(shoppingCartService::delete)
                .orElseThrow();
    }

    private ShoppingCartReadDto getShoppingCart(String sessionId) {
        return shoppingCartService.findBy(sessionId).orElseThrow();
    }

    private List<OrderLineReadDto> createOrderLines(OrderReadDto orderDto, ShoppingCartReadDto shoppingCart) {
        return shoppingCart.getItems().stream()
                .map(it -> new OrderLineCreateEditDto(orderDto.getId(), it.getReservationSeat().getId(), it.getReservationSeat().getPrice()))
                .map(orderLineService::create)
                .toList();
    }

    @Transactional
    public Optional<OrderReadDto> update(Long id, OrderCreateEditDto order) {
        return orderRepository.findById(id)
                .map(entity -> orderCreateEditMapper.map(order, entity))
                .map(orderRepository::saveAndFlush)
                .map(orderReadMapper::map);
    }
}