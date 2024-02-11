package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.OrderLineRepository;
import org.artem.flight.system.dto.OrderLineCreateEditDto;
import org.artem.flight.system.dto.OrderLineReadDto;
import org.artem.flight.system.mapper.OrderLineCreateEditMapper;
import org.artem.flight.system.mapper.OrderLineReadMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderLineService {


    private final OrderLineRepository orderRepository;
    private final OrderLineReadMapper orderLineReadMapper = Mappers.getMapper(OrderLineReadMapper.class);
    private final OrderLineCreateEditMapper orderLineCreateEditMapper;

    public List<OrderLineReadDto> findAll() {
        return orderRepository.findAll().stream()
                .map(orderLineReadMapper::map)
                .toList();
    }

    public Optional<OrderLineReadDto> findById(Long id) {
        return orderRepository.findById(id)
                .map(orderLineReadMapper::map);
    }

    @Transactional
    public OrderLineReadDto create(OrderLineCreateEditDto orderLineDto) {
        return Optional.of(orderLineDto)
                .map(orderLineCreateEditMapper::map)
                .map(orderRepository::save)
                .map(orderLineReadMapper::map)
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
}
