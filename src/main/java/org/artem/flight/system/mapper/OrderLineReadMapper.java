package org.artem.flight.system.mapper;

import org.artem.flight.system.database.entity.OrderLine;
import org.artem.flight.system.dto.OrderLineReadDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderLineReadMapper {

    OrderLineReadDto map(OrderLine order);
}