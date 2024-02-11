package org.artem.flight.system.mapper;

import org.artem.flight.system.database.entity.Order;
import org.artem.flight.system.dto.OrderReadDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderReadMapper {

    OrderReadDto map(Order order);
}